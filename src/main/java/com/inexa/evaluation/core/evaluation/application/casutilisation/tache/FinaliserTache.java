package com.inexa.evaluation.core.evaluation.application.casutilisation.tache;

import com.inexa.evaluation.core.evaluation.application.commande.tache.FinaliserTacheCommande;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.UUID;

/**
 * <p>Cas d'utilisation pour la finalisation d'une {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-06
 */
public class FinaliserTache {

  private final TacheRepositoryPort tacheRepositoryPort;

  public FinaliserTache(
      TacheRepositoryPort tacheRepositoryPort) {
    this.tacheRepositoryPort = tacheRepositoryPort;
  }

  public void finaliser(FinaliserTacheCommande commande) throws Exception {
    UUID tacheId = UUID.fromString(commande.getTacheId());
    int tempsRealise = commande.getTempsRealise();

    //Recherche du Tache en fonction d'un ID;
    Tache tache = this.tacheRepositoryPort.rechercheParId(tacheId);
    tache.setTempsFinTache(tempsRealise);
    tache.finaliserTache();

    //Enregistrer la modification
    this.tacheRepositoryPort.finaliserTache(tache);
  }

}
