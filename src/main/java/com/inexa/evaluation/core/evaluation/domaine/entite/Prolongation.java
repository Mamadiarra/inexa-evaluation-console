package com.inexa.evaluation.core.evaluation.domaine.entite;

import java.util.Objects;
import java.util.UUID;

/**
 * <p>Entité domaine {@link Prolongation}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class Prolongation {

  protected UUID id;
  protected String motif;
  protected int temps;
  protected Tache tache;

  public Prolongation() {}

  public Prolongation(String motif, int temps, Tache tache) {
    this.id = UUID.randomUUID();
    this.motif = motif;
    this.tache = tache;
    this.temps = temps;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getMotif() {
    return motif;
  }

  public void setMotif(String motif) {
    this.motif = motif;
  }

  public Tache getTache() {
    return tache;
  }

  public void setTache(Tache tache) {
    this.tache = tache;
  }

  public int getTemps() {
    return temps;
  }

  public void setTemps(int temps) {
    this.temps = temps;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Prolongation that = (Prolongation) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, motif, temps);
  }

  @Override
  public String toString() {
    return "Prolongation{" +
        "id=" + id +
        ", motif='" + motif + '\'' +
        ", temps=" + temps +
        '}';
  }
}
