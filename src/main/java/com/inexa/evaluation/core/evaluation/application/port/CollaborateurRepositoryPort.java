package com.inexa.evaluation.core.evaluation.application.port;

import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.List;
import java.util.UUID;

/**
 * <p>Repository port de l'entité domaine {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public interface CollaborateurRepositoryPort {

    List<Collaborateur> lister();

    void ajouter(Collaborateur collaborateur);

    Collaborateur rechercherParId(UUID id);

    void ajouterTache (UUID uuid, Tache tacheAEnregistrer);
}
