package com.inexa.evaluation.core.evaluation.domaine.entite;

import com.inexa.evaluation.core.evaluation.domaine.objetvaleur.TacheStatut;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * <p>Entité domaine {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class Tache implements Serializable {

  private UUID id;
  private String libelle;
  private TacheStatut statut;
  private int estimation;
  private int tempsFinTache;
  private Collaborateur collaborateur;
  private Projet projet;
  private Set<Prolongation> prolongations = new HashSet<>();
  private Set<Imprevu> imprevus = new HashSet<>();

  public Tache() {
  }

  public Tache(String libelle, int estimation, Collaborateur collaborateur, Projet projet) {
    this.id = UUID.randomUUID();
    this.statut = TacheStatut.DEMARRER;
    this.libelle = libelle;
    this.estimation = estimation;
    this.collaborateur = collaborateur;
    this.projet = projet;
  }

  /**
   * <p>Methode servant à finaliser la tache.</p>
   */
  public void finaliserTache() {
    this.statut = TacheStatut.TERMINER;
  }

  /**
   * <p>Methode servant à verifier si la tache est terminée.</p>
   *
   * @return vrai si la tache est terminée et faux si la tache est toujours en cours.
   */
  public Boolean verifierSiTacheTerminer() {
    return this.statut == TacheStatut.TERMINER;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getLibelle() {
    return libelle;
  }

  public void setLibelle(String libelle) {
    this.libelle = libelle;
  }

  public TacheStatut getStatut() {
    return statut;
  }

  public void setStatut(TacheStatut statut) {
    this.statut = statut;
  }

  public int getEstimation() {
    return estimation;
  }

  public void setEstimation(int estimation) {
    this.estimation = estimation;
  }

  public int getTempsFinTache() {
    return tempsFinTache;
  }

  public void setTempsFinTache(int tempsFinTache) {
    this.tempsFinTache = tempsFinTache;
  }

  public Collaborateur getCollaborateur() {
    return collaborateur;
  }

  public void setCollaborateur(
      Collaborateur collaborateur) {
    this.collaborateur = collaborateur;
  }

  public Projet getProjet() {
    return projet;
  }

  public void setProjet(Projet projet) {
    this.projet = projet;
  }

  public Set<Prolongation> getProlongations() {
    return prolongations;
  }

  public void setProlongations(
      Set<Prolongation> prolongations) {
    this.prolongations = prolongations;
  }

  public Set<Imprevu> getImprevus() {
    return imprevus;
  }

  public void setImprevus(
      Set<Imprevu> imprevus) {
    this.imprevus = imprevus;
  }

  public void ajouterProlongation(Prolongation prolongation) {
    this.prolongations.add(prolongation);
  }

  public void ajouterImprevu(Imprevu imprevu) {
    this.imprevus.add(imprevu);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Tache tache = (Tache) o;
    return Objects.equals(id, tache.id);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(id, libelle, statut, estimation, tempsFinTache, prolongations, imprevus);
  }

  @Override
  public String toString() {
    return "tache{" +
        "id=" + id +
        ", libelle='" + libelle + '\'' +
        ", statut=" + statut +
        ", estimation=" + estimation +
        ", tempsFinTache=" + tempsFinTache +
        '}';
  }
}
