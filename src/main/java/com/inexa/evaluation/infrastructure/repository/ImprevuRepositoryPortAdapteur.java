package com.inexa.evaluation.infrastructure.repository;

import com.inexa.evaluation.core.evaluation.application.port.ImprevuRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Repository port adapteur de l'entité domaine {@link Imprevu}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class ImprevuRepositoryPortAdapteur implements ImprevuRepositoryPort {

  private static List<Imprevu> listeImprevu = new ArrayList<>();

  @Override
  public List<Imprevu> lister() {
    return listeImprevu;
  }

  @Override
  public void ajouter(Imprevu imprevu) {
    listeImprevu.add(imprevu);
  }
}
