package com.inexa.evaluation.core.evaluation.application.port;

import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import java.util.List;

/**
 * <p>Repository port de l'entité domaine {@link Prolongation}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public interface ProlongationRepositoryPort {

  List<Prolongation> lister();

  void ajouter(Prolongation prolongation);
}
