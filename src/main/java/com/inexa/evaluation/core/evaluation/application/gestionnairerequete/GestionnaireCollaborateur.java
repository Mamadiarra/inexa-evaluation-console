package com.inexa.evaluation.core.evaluation.application.gestionnairerequete;

import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import java.util.List;
import java.util.UUID;

/**
 * <p>Gestionnaire de l'entité domaine {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public class GestionnaireCollaborateur implements GestionnaireRequete<UUID, Collaborateur> {

  private final CollaborateurRepositoryPort collaborateurRepositoryPort;

  public GestionnaireCollaborateur(
      CollaborateurRepositoryPort collaborateurRepositoryPort) {
    this.collaborateurRepositoryPort = collaborateurRepositoryPort;
  }

  @Override
  public List<Collaborateur> lister() {
    return this.collaborateurRepositoryPort.lister();
  }
}
