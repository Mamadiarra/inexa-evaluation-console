package com.inexa.evaluation.core.evaluation.application.casutilisation.collaborateur;

import com.inexa.evaluation.core.evaluation.application.commande.collaborateur.CreerCollaborateurCommande;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;

/**
 * <p>Cas d'utilisation pour la création d'un {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class CreerCollaborateur {

  private final CollaborateurRepositoryPort collaborateurRepositoryPort;

  public CreerCollaborateur(
      CollaborateurRepositoryPort collaborateurRepositoryPort) {
    this.collaborateurRepositoryPort = collaborateurRepositoryPort;
  }

  public void ajouter(CreerCollaborateurCommande commande) {
    Collaborateur collaborateur = this.genererCollaborateur(commande);
    this.collaborateurRepositoryPort.ajouter(collaborateur);
  }

  /**
   * Methode de génération d'un collaborateur en fonction de la commande passé en paramètre.
   *
   * @param commande correspond aux données saisis lors de l'enregistrement d'un collaborateur.
   * @return un objet collaborateur
   */
  private Collaborateur genererCollaborateur(CreerCollaborateurCommande commande) {
    String nom = commande.getNom();
    String prenom = commande.getPrenom();
    String fonction = commande.getFonction();
    return new Collaborateur(nom, prenom, fonction);
  }

}
