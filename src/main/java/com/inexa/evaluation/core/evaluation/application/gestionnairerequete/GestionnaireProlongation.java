package com.inexa.evaluation.core.evaluation.application.gestionnairerequete;

import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.port.ProlongationRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import java.util.List;
import java.util.UUID;

/**
 * <p>Gestionnaire de l'entité domaine {@link Prolongation}</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class GestionnaireProlongation implements GestionnaireRequete<UUID, Prolongation> {

  private final ProlongationRepositoryPort prolongationRepositoryPort;

  public GestionnaireProlongation(
      ProlongationRepositoryPort prolongationRepositoryPort) {
    this.prolongationRepositoryPort = prolongationRepositoryPort;
  }

  @Override
  public List<Prolongation> lister() {
    return this.prolongationRepositoryPort.lister();
  }
}
