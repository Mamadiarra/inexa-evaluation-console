package com.inexa.evaluation.core.evaluation.application.gestionnairecommande.tache;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.casutilisation.tache.FinaliserTache;
import com.inexa.evaluation.core.evaluation.application.commande.tache.FinaliserTacheCommande;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import javax.validation.Valid;

/**
 * <p>Gestionnaire de la commande {@link FinaliserTacheCommande}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-06
 */
public class GestionnaireFinaliserTache implements GestionnaireCommande<FinaliserTacheCommande> {

  private final FinaliserTache finaliserTache;

  public GestionnaireFinaliserTache(TacheRepositoryPort tacheRepositoryPort) {
    this.finaliserTache = new FinaliserTache(tacheRepositoryPort);
  }

  @Override
  public void execute(@Valid FinaliserTacheCommande commande) throws Exception {
    this.finaliserTache.finaliser(commande);
  }
}
