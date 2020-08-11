package com.inexa.evaluation.core.evaluation.application.casutilisation.tache;

import com.inexa.evaluation.core.evaluation.application.commande.tache.CreerTacheCommande;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.UUID;

/**
 * <p>Cas d'utilisation pour la création d'une {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class CreerTache {

  private final TacheRepositoryPort tacheRepositoryPort;
  private final CollaborateurRepositoryPort collaborateurRepositoryPort;
  private final ProjetRepositoryPort projetRepositoryPort;

  public CreerTache(
      TacheRepositoryPort tacheRepositoryPort,
      CollaborateurRepositoryPort collaborateurRepositoryPort,
      ProjetRepositoryPort projetRepositoryPort) {
    this.tacheRepositoryPort = tacheRepositoryPort;
    this.collaborateurRepositoryPort = collaborateurRepositoryPort;
    this.projetRepositoryPort = projetRepositoryPort;
  }

  public void ajouter(CreerTacheCommande commande) throws Exception {
    UUID collaborateurId = UUID.fromString(commande.getCollaborateurId());
    UUID projetId = UUID.fromString(commande.getProjetId());
    String libelle = commande.getLibelle();
    int estimation = commande.getEstimation();

    //Recherche du collaborateur et du projet en fonction de leur ID;
    Collaborateur collaborateur = this.collaborateurRepositoryPort.rechercherParId(collaborateurId);

    //Verifié si le collaborateur a une tache en cours
    this.verificationTacheCollaborateur(collaborateur);

    Projet projet = this.projetRepositoryPort.rechercherParId(projetId);

    //Génération de la tache
    Tache tache = new Tache(libelle, estimation, collaborateur, projet);

    //Enregistrement de la tache
    Tache tacheEnregistrer = this.tacheRepositoryPort.ajouter(tache);

    //Assigner la tache au projet et au collaborateur.
    this.collaborateurRepositoryPort.ajouterTache(collaborateur.getId(), tacheEnregistrer);
    this.projetRepositoryPort.ajouterTache(projet.getId(), tacheEnregistrer);
  }

  public void verificationTacheCollaborateur(Collaborateur collaborateur) throws Exception {
    if (collaborateur.verifierSiUneTacheEstEnCours()) {
      throw new Exception("Cet collaborateur a déjà une tache en cours");
    }
  }


}
