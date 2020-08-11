package com.inexa.evaluation.core.evaluation.application.commande.tache;

import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import javax.validation.constraints.NotBlank;

/**
 * <p>Commande pour la finalisation d'une {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-06
 */
public class FinaliserTacheCommande {

  @NotBlank
  private String tacheId;

  @NotBlank
  private int tempsRealise;

  public String getTacheId() {
    return tacheId;
  }

  public void setTacheId(String tacheId) {
    this.tacheId = tacheId;
  }

  public int getTempsRealise() {
    return tempsRealise;
  }

  public void setTempsRealise(int tempsRealise) {
    this.tempsRealise = tempsRealise;
  }
}
