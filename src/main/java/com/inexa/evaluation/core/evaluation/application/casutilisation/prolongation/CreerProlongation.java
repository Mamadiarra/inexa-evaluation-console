package com.inexa.evaluation.core.evaluation.application.casutilisation.prolongation;

import com.inexa.evaluation.core.evaluation.application.commande.prolongation.CreerProlongationCommande;
import com.inexa.evaluation.core.evaluation.application.port.ProlongationRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.UUID;

/**
 * <p>Cas d'utilisation pour la création d'un {@link Prolongation}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class CreerProlongation {

  private final ProlongationRepositoryPort prolongationRepositoryPort;
  private final TacheRepositoryPort tacheRepositoryPort;

  public CreerProlongation(
      ProlongationRepositoryPort prolongationRepositoryPort,
      TacheRepositoryPort tacheRepositoryPort) {
    this.prolongationRepositoryPort = prolongationRepositoryPort;
    this.tacheRepositoryPort = tacheRepositoryPort;
  }

  public void ajouter(CreerProlongationCommande commande) {
    UUID tacheId = UUID.fromString(commande.getTacheId());
    String motif = commande.getMotif();
    int temps = commande.getTemps();

    // Recherche de la tache par son ID;
    Tache tache = this.tacheRepositoryPort.rechercheParId(tacheId);

    //Génération de la prolongation
    Prolongation prolongation = new Prolongation(motif, temps, tache);

    //Enregistement de la prolongation
    this.prolongationRepositoryPort.ajouter(prolongation);

    //Assigner la prolongation à la tache.
    this.tacheRepositoryPort.ajouterProlongation(tache.getId(), prolongation);
  }

}
