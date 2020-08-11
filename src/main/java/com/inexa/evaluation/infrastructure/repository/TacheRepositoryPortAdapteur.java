package com.inexa.evaluation.infrastructure.repository;

import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>Repository port adapteur de l'entité domaine {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class TacheRepositoryPortAdapteur implements TacheRepositoryPort {

  private static List<Tache> listeTache = new ArrayList<>();

  @Override
  public List<Tache> lister() {
    return listeTache;
  }

  @Override
  public Tache ajouter(Tache tache) {
    listeTache.add(tache);
    return tache;
  }

  @Override
  public Tache rechercheParId(UUID tacheId) {
    Tache tache = listeTache.stream()
        .filter(c -> c.getId().equals(tacheId)).findFirst()
        .orElse(null);
    return tache;
  }

  @Override
  public void ajouterProlongation(UUID id, Prolongation prolongationAjouter) {
    Tache tache = listeTache.stream()
        .filter(c -> c.getId().equals(id)).findAny().orElse(null);

    if (tache != null) {
      listeTache.remove(tache);
      tache.ajouterProlongation(prolongationAjouter);
      listeTache.add(tache);
    }
  }

  @Override
  public void ajouterImprevu(UUID id, Imprevu imprevu) {
    Tache tache = listeTache.stream()
        .filter(c -> c.getId().equals(id)).findAny().orElse(null);

    if (tache != null) {
      listeTache.remove(tache);
      tache.ajouterImprevu(imprevu);
      listeTache.add(tache);
    }
  }

  @Override
  public void finaliserTache(Tache tacheAjouter) {
    Tache tache = listeTache.stream()
        .filter(c -> c.getId().equals(tacheAjouter.getId())).findAny().orElse(null);

    if (tache != null) {
      listeTache.remove(tache);
      listeTache.add(tacheAjouter);
    }
  }
}
