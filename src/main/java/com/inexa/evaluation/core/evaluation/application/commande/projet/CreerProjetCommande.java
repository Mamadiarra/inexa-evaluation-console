package com.inexa.evaluation.core.evaluation.application.commande.projet;

import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;
import javax.validation.constraints.NotBlank;

/**
 * <p>Commande pour la création d'un {@link Projet}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class CreerProjetCommande {

  @NotBlank
  private String nom;

  @NotBlank
  private String description;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
