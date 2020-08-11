package com.inexa.evaluation.core.evaluation.application.commande.tache;

import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import javax.validation.constraints.NotBlank;

/**
 * <p>Commande pour la création d'une {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class CreerTacheCommande {

  @NotBlank
  private String libelle;

  @NotBlank
  private int estimation;

  @NotBlank
  private String CollaborateurId;

  @NotBlank
  private String ProjetId;

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public int getEstimation() {
    return estimation;
  }

  public void setEstimation(int estimation) {
    this.estimation = estimation;
  }

  public String getCollaborateurId() {
    return CollaborateurId;
  }

  public void setCollaborateurId(String collaborateurId) {
    CollaborateurId = collaborateurId;
  }

  public String getProjetId() {
    return ProjetId;
  }

  public void setProjetId(String projetId) {
    ProjetId = projetId;
  }
}
