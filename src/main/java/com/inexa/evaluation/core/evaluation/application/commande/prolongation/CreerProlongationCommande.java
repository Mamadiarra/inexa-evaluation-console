package com.inexa.evaluation.core.evaluation.application.commande.prolongation;

import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import javax.validation.constraints.NotBlank;

/**
 * <p>Commande pour la création d'un {@link Prolongation}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class CreerProlongationCommande {

  @NotBlank
  private String motif;

  @NotBlank
  private int temps;

  @NotBlank
  private String tacheId;

  public String getMotif() {
    return motif;
  }

  public void setMotif(String motif) {
    this.motif = motif;
  }

  public int getTemps() {
    return temps;
  }

  public void setTemps(int temps) {
    this.temps = temps;
  }

  public String getTacheId() {
    return tacheId;
  }

  public void setTacheId(String tacheId) {
    this.tacheId = tacheId;
  }
}
