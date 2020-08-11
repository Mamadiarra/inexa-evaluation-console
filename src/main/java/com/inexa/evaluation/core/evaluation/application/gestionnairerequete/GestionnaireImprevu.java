package com.inexa.evaluation.core.evaluation.application.gestionnairerequete;

import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.port.ImprevuRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import java.util.List;
import java.util.UUID;

/**
 * <p>Gestionnaire de l'entité domaine {@link Imprevu}</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class GestionnaireImprevu implements GestionnaireRequete<UUID, Imprevu> {

  private final ImprevuRepositoryPort imprevuRepositoryPort;

  public GestionnaireImprevu(
      ImprevuRepositoryPort imprevuRepositoryPort) {
    this.imprevuRepositoryPort = imprevuRepositoryPort;
  }

  @Override
  public List<Imprevu> lister() {
    return this.imprevuRepositoryPort.lister();
  }
}
