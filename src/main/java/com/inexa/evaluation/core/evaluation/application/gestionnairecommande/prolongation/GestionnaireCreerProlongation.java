package com.inexa.evaluation.core.evaluation.application.gestionnairecommande.prolongation;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.casutilisation.prolongation.CreerProlongation;
import com.inexa.evaluation.core.evaluation.application.commande.prolongation.CreerProlongationCommande;
import com.inexa.evaluation.core.evaluation.application.port.ProlongationRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;

/**
 * <p>Gestionnaire de la commande {@link CreerProlongationCommande}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class GestionnaireCreerProlongation implements
    GestionnaireCommande<CreerProlongationCommande> {

  private final CreerProlongation creerProlongation;

  public GestionnaireCreerProlongation(
      ProlongationRepositoryPort prolongationRepositoryPort,
      TacheRepositoryPort tacheRepositoryPort) {
    this.creerProlongation = new CreerProlongation(prolongationRepositoryPort, tacheRepositoryPort);
  }

  @Override
  public void execute(CreerProlongationCommande commande) throws Exception {
    this.creerProlongation.ajouter(commande);
  }
}
