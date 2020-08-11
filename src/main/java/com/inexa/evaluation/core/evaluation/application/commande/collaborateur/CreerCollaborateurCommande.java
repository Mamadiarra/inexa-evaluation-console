package com.inexa.evaluation.core.evaluation.application.commande.collaborateur;

import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import javax.validation.constraints.NotBlank;

/**
 * <p>Commande pour la création d'un {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class CreerCollaborateurCommande {

  @NotBlank
  private String nom;

  @NotBlank
  private String prenom;

  @NotBlank
  private String fonction;

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getFonction() {
    return fonction;
  }

  public void setFonction(String fonction) {
    this.fonction = fonction;
  }
}
