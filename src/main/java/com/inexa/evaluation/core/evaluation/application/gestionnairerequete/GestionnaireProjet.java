package com.inexa.evaluation.core.evaluation.application.gestionnairerequete;

import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;
import java.util.List;
import java.util.UUID;

/**
 * <p>Gestionnaire de l'entité domaine {@link Projet}</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class GestionnaireProjet implements GestionnaireRequete<UUID, Projet> {

  private final ProjetRepositoryPort projetRepositoryPort;

  public GestionnaireProjet(
      ProjetRepositoryPort projetRepositoryPort) {
    this.projetRepositoryPort = projetRepositoryPort;
  }

  @Override
  public List<Projet> lister() {
    return this.projetRepositoryPort.lister();
  }
}
