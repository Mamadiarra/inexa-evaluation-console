package com.inexa.evaluation.core.evaluation.application.casutilisation.imprevu;

import com.inexa.evaluation.core.evaluation.application.commande.imprevu.CreerImprevuCommande;
import com.inexa.evaluation.core.evaluation.application.port.ImprevuRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.UUID;

/**
 * <p>Cas d'utilisation pour la création d'un {@link Imprevu}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class CreerImprevu {

  private final ImprevuRepositoryPort imprevuRepositoryPort;
  private final TacheRepositoryPort tacheRepositoryPort;

  public CreerImprevu(
      ImprevuRepositoryPort imprevuRepositoryPort,
      TacheRepositoryPort tacheRepositoryPort) {
    this.imprevuRepositoryPort = imprevuRepositoryPort;
    this.tacheRepositoryPort = tacheRepositoryPort;
  }

  public void ajouter(CreerImprevuCommande commande) {
    UUID tacheId = UUID.fromString(commande.getTacheId());
    String description = commande.getMotif();
    int temps = commande.getTemps();

    // Recherche de la tache par son ID;
    Tache tache = this.tacheRepositoryPort.rechercheParId(tacheId);

    //Génération de l'imprévu
    Imprevu imprevu = new Imprevu(description, temps, tache);

    //Enregistement de l'imprévu
    this.imprevuRepositoryPort.ajouter(imprevu);

    //Assigner l'imprévu à la tache.
    this.tacheRepositoryPort.ajouterImprevu(tache.getId(), imprevu);
  }

}
