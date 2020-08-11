package com.inexa.evaluation.core.evaluation.domaine.entite;

/**
 * <p>Entité domaine {@link Imprevu}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class Imprevu extends Prolongation{

  public Imprevu() {
  }

  public Imprevu(String motif, int temps,
      Tache tache) {
    super(motif, temps, tache);
  }

  @Override
  public String toString() {
    return "Imprevu{" +
        "id=" + id +
        ", motif='" + motif + '\'' +
        ", temps=" + temps +
        '}';
  }
}
