package com.inexa.evaluation.core.evaluation.application.port;

import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import java.util.List;

/**
 * <p>Repository port de l'entité domaine {@link Imprevu}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public interface ImprevuRepositoryPort {

  List<Imprevu> lister();

  void ajouter(Imprevu imprevu);
}
