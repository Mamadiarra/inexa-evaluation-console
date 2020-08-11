package com.inexa.evaluation.core.evaluation.application.gestionnairerequete;

import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.List;
import java.util.UUID;

/**
 * <p>Gestionnaire de l'entité domaine {@link Tache}</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class GestionnaireTache implements GestionnaireRequete<UUID, Tache> {

  private final TacheRepositoryPort tacheRepositoryPort;

  public GestionnaireTache(
      TacheRepositoryPort tacheRepositoryPort) {
    this.tacheRepositoryPort = tacheRepositoryPort;
  }

  @Override
  public List<Tache> lister() {
    return this.tacheRepositoryPort.lister();
  }
}
