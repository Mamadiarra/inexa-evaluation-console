package com.inexa.evaluation.infrastructure.repository;

import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>Repository port adapteur de l'entité domaine {@link Projet}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class ProjetRepositoryPortAdapteur implements ProjetRepositoryPort {

  private static List<Projet> listeProjet = new ArrayList<>();

  @Override
  public List<Projet> lister() {
    return listeProjet;
  }

  @Override
  public void ajouter(Projet projet) {
    listeProjet.add(projet);
  }

  @Override
  public Projet rechercherParId(UUID projetId) {
    Projet projet = listeProjet.stream()
        .filter(c -> c.getId().equals(projetId)).findFirst()
        .orElse(null);
    return projet;
  }

  @Override
  public void ajouterTache(UUID id, Tache tache) {
    Projet projet = listeProjet.stream()
        .filter(c -> c.getId().equals(id)).findAny().orElse(null);

    if (projet != null) {
      listeProjet.remove(projet);
      projet.ajouterTache(tache);
      listeProjet.add(projet);
    }
  }
}
