package com.inexa.evaluation.core.evaluation.application.port;

import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.List;
import java.util.UUID;

/**
 * <p>Repository port de l'entité domaine {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public interface TacheRepositoryPort {

  List<Tache> lister();

  Tache ajouter(Tache tache);

  void ajouterProlongation(UUID id, Prolongation prolongation);

  Tache rechercheParId(UUID tacheId);

  void ajouterImprevu(UUID id, Imprevu imprevu);

  void finaliserTache(Tache tache);
}
