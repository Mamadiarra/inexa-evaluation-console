package com.inexa.evaluation.core.evaluation.application.port;

import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.List;
import java.util.UUID;

/**
 * <p>Repository port de l'entité domaine {@link Projet}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public interface ProjetRepositoryPort {

  List<Projet> lister();

  void ajouter(Projet projet);

  Projet rechercherParId(UUID projetId);

  void ajouterTache(UUID id, Tache tache);
}
