package com.inexa.evaluation.core.evaluation.domaine.entite;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * <p>Entité domaine {@link Projet}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public class Projet implements Serializable {

  private UUID id;
  private String nom;
  private String description;
  private Set<Tache> taches = new HashSet<>();

  public Projet() {}

  public Projet(String nom, String description) {
    this.id = UUID.randomUUID();
    this.nom = nom;
    this.description = description;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

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

  public Set<Tache> getTaches() {
    return taches;
  }

  public void setTaches(Set<Tache> taches) {
    this.taches = taches;
  }

  /**
   * <p>Methode d'ajout de tache au projet.</p>
   * @param tache à ajouter au projet
   */
  public void ajouterTache(Tache tache) {
    this.taches.add(tache);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Projet projet = (Projet) o;
    return Objects.equals(id, projet.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nom, description, taches);
  }

  @Override
  public String toString() {
    return "Projet{" +
        "id=" + id +
        ", nom='" + nom + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}
