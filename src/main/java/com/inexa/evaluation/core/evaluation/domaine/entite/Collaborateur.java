package com.inexa.evaluation.core.evaluation.domaine.entite;

import com.inexa.evaluation.core.evaluation.domaine.objetvaleur.TacheStatut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>Entité domaine {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public class Collaborateur implements Serializable {

  private UUID id;
  private String nom;
  private String prenom;
  private String fonction;
  private List<Tache> taches = new ArrayList<>();

  public Collaborateur() {
  }

  public Collaborateur(String nom, String prenom, String fonction) {
    this.id = UUID.randomUUID();
    this.nom = nom;
    this.prenom = prenom;
    this.fonction = fonction;
  }

  /**
   * <p>Verifier si l'utilisateur à une tache en cours.</p>
   *
   * @return vrai si il a une tache en cours et faux sinon.
   */
  public Boolean verifierSiUneTacheEstEnCours() {
    return this.taches.stream().filter(tache -> tache.getStatut() == TacheStatut.DEMARRER).count()
        > 0;
  }

  /**
   * <p>Methode de calcul de note globale de toutes ses taches.</p>
   *
   * @return un entier qui correspond à la note.
   */
  public double noteGlobale() {

    //Récupération des taches terminées
    Set<Tache> listTaches = this.taches.stream().filter(tache -> {
      return tache.getStatut() == TacheStatut.TERMINER;
    }).collect(Collectors.toSet());

    //Récupération des temps d'imprévu global
    int tempsRealiseImprevuGlobal = listTaches.stream().mapToInt(tache -> {
      int tempsRealise = tache.getTempsFinTache();
      int tempsImprevu = tache.getImprevus().stream().mapToInt(Imprevu::getTemps).sum();
      return (tempsRealise - tempsImprevu);
    }).sum();

    //Récupération des temps de prolongation global
    int tempsProlongationGlobal = listTaches.stream().mapToInt(tache -> {
      int budgetTemps = tache.getEstimation();
      int tempsProlongation = tache.getProlongations().stream()
          .mapToInt(Prolongation::getTemps).sum();
      return (budgetTemps + tempsProlongation);
    }).sum();

    double note = 0;
    note = tempsRealiseImprevuGlobal - tempsProlongationGlobal;
    double divisionNote = (note / tempsProlongationGlobal);
    double noteGlobal = 10 - (divisionNote * 10);

    // Retour de la note globale
    return noteGlobal >= 0 ? noteGlobal : 0;
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

  public List<Tache> getTaches() {
    return taches;
  }

  public void setTaches(List<Tache> taches) {
    this.taches = taches;
  }

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
    Collaborateur that = (Collaborateur) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nom, prenom, fonction, taches);
  }

  @Override
  public String toString() {
    return "Collaborateur{" +
        "id=" + id +
        ", nom='" + nom + '\'' +
        ", prenom='" + prenom + '\'' +
        ", fonction='" + fonction + '\'' +
        '}';
  }
}
