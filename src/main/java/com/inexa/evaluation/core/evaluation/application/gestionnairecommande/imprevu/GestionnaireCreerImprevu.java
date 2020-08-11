package com.inexa.evaluation.core.evaluation.application.gestionnairecommande.imprevu;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.casutilisation.imprevu.CreerImprevu;
import com.inexa.evaluation.core.evaluation.application.commande.imprevu.CreerImprevuCommande;
import com.inexa.evaluation.core.evaluation.application.port.ImprevuRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;

/**
 * <p>Gestionnaire de la commande {@link CreerImprevuCommande}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class GestionnaireCreerImprevu implements GestionnaireCommande<CreerImprevuCommande> {

  private final CreerImprevu creerImprevu;

  public GestionnaireCreerImprevu(
      ImprevuRepositoryPort imprevuRepositoryPort,
      TacheRepositoryPort tacheRepositoryPort) {
    this.creerImprevu = new CreerImprevu(imprevuRepositoryPort, tacheRepositoryPort);
  }

  @Override
  public void execute(CreerImprevuCommande commande) throws Exception {
    this.creerImprevu.ajouter(commande);
  }
}
