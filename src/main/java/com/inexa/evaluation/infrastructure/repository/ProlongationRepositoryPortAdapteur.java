package com.inexa.evaluation.infrastructure.repository;

import com.inexa.evaluation.core.evaluation.application.port.ProlongationRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Repository port adapteur de l'entité domaine {@link Prolongation}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class ProlongationRepositoryPortAdapteur implements ProlongationRepositoryPort {

  private static List<Prolongation> listeProlongation = new ArrayList<>();

  @Override
  public List<Prolongation> lister() {
    return listeProlongation;
  }

  @Override
  public void ajouter(Prolongation prolongation) {
    listeProlongation.add(prolongation);
  }
}
