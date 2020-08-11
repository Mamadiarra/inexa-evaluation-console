package com.inexa.evaluation.infrastructure.repository;

import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>Repository port adapteur de l'entité domaine {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public class CollaborateurRepositoryPortAdapteur implements CollaborateurRepositoryPort {

  private static List<Collaborateur> listeCollaborateur = new ArrayList<>();

  @Override
  public List<Collaborateur> lister() {
    return listeCollaborateur;
  }

  @Override
  public void ajouter(Collaborateur collaborateur) {
    listeCollaborateur.add(collaborateur);
  }

  @Override
  public Collaborateur rechercherParId(UUID id) {
    Collaborateur collaborateur = listeCollaborateur.stream()
        .filter(c -> c.getId().equals(id)).findAny().orElse(null);
    return collaborateur;
  }

  @Override
  public void ajouterTache(UUID id, Tache tacheAEnregistrer) {
    Collaborateur collaborateur = listeCollaborateur.stream()
        .filter(c -> c.getId().equals(id)).findAny().orElse(null);

    if (collaborateur != null) {
      listeCollaborateur.remove(collaborateur);
      collaborateur.ajouterTache(tacheAEnregistrer);
      listeCollaborateur.add(collaborateur);
    }
  }
}
