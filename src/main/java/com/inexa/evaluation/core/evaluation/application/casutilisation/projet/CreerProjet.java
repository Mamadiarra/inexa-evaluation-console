package com.inexa.evaluation.core.evaluation.application.casutilisation.projet;

import com.inexa.evaluation.core.evaluation.application.commande.projet.CreerProjetCommande;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;

/**
 * <p>Cas d'utilisation pour la création d'un {@link Projet}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class CreerProjet {

  private final ProjetRepositoryPort projetRepositoryPort;

  public CreerProjet(
      ProjetRepositoryPort projetRepositoryPort) {
    this.projetRepositoryPort = projetRepositoryPort;
  }

  public void ajouter(CreerProjetCommande commande) {
    Projet projet = this.genererProjet(commande);
    this.projetRepositoryPort.ajouter(projet);
  }

  /**
   * <p>Methode de génération d'un projet en fonction de la commande passé en paramètre.</p>
   *
   * @param commande correspond aux données saisis lors de l'enregistrement d'un projet.
   * @return un objet projet
   */
  private Projet genererProjet(CreerProjetCommande commande) {
    String nom = commande.getNom();
    String description = commande.getDescription();
    return new Projet(nom, description);
  }

}
