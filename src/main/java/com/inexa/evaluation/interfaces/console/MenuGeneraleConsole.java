package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.infrastructure.repository.CollaborateurRepositoryPortAdapteur;
import com.inexa.evaluation.infrastructure.repository.ImprevuRepositoryPortAdapteur;
import com.inexa.evaluation.infrastructure.repository.ProjetRepositoryPortAdapteur;
import com.inexa.evaluation.infrastructure.repository.ProlongationRepositoryPortAdapteur;
import com.inexa.evaluation.infrastructure.repository.TacheRepositoryPortAdapteur;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import org.beryx.textio.TextIO;

/**
 * <p>Console  du menu général.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public class MenuGeneraleConsole {

  /**
   * <p>Initialisation de l'application en affichant le menu général.</p>
   */
  public static void main(String[] args) throws Exception {
    choixMenuGeneral();
  }

  /**
   * <p>Initialisation du menu général.</p>
   *
   * @return un tableau de menu à afficher
   */
  private static List<String> initialisationMenuGeneral() {
    return Arrays
        .asList("Projet", "Collaborateur", "Tache", "Prolongation", "Imprevu", "Fin de tache",
            "Evaluation");
  }

  /**
   * <p>Methode de choix du menu.</p>
   */
  public static void choixMenuGeneral() throws Exception {

    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextIO textIO = universalServiceConsole.initialisationTextio();
    universalServiceConsole.affichageTitre("MENU GENERAL");

    String choix = textIO.newStringInputReader()
        .withNumberedPossibleValues(initialisationMenuGeneral())
        .read("Choisissez un menu");
    afficherMenu(choix);
  }

  /**
   * <p>Methode d'appel de console en fonction du choix du menu.</p>
   */
  private static void afficherMenu(String choix) throws Exception {
    switch (choix) {
      case "Projet":
        choixMenuProjet();
        break;
      case "Collaborateur":
        choixMenuCollaborateur();
        break;
      case "Tache":
        choixMenuTache();
        break;
      case "Prolongation":
        choixMenuProlongation();
        break;
      case "Imprevu":
        choixMenuImprevu();
        break;
      case "Fin de tache":
        choixMenuFinTache();
        break;
      case "Evaluation":
        choixMenuEvaluation();
        break;
      default:
        System.out.println("Choix non disponible");
    }
  }

  /**
   * <p>Methode de choix de la fonctionnalité projet.</p>
   */
  private static void choixMenuProjet() throws Exception {
    //Instanciation de la classe concrete de l'interface ProjetRepositoryPort
    ProjetRepositoryPortAdapteur projetRepositoryPortAdapteur =
        new ProjetRepositoryPortAdapteur();
    ProjetConsole projetConsole = new ProjetConsole(
        projetRepositoryPortAdapteur);

    //Affichage du sous menu collaborateur
    projetConsole.choixSousMenuProjet();
  }

  /**
   * <p>Methode de choix de la fonctionnalité collaborateur.</p>
   */
  private static void choixMenuCollaborateur() throws Exception {
    //Instanciation de la classe concrete de l'interface CollaborateurRepositoryPort
    CollaborateurRepositoryPortAdapteur collaborateurRepositoryPortAdapteur =
        new CollaborateurRepositoryPortAdapteur();
    CollaborateurConsole collaborateurConsole = new CollaborateurConsole(
        collaborateurRepositoryPortAdapteur);

    //Affichage du sous menu collaborateur
    collaborateurConsole.choixSousMenuCollaborateur();
  }

  /**
   * <p>Methode de choix de la fonctionnalité tache.</p>
   */
  private static void choixMenuTache() throws Exception {
    //Instanciation de la classe concrete de l'interface TacheRepositoryPort
    TacheRepositoryPortAdapteur tacheRepositoryPortAdapteur =
        new TacheRepositoryPortAdapteur();
    CollaborateurRepositoryPortAdapteur collaborateurRepositoryPortAdapteur =
        new CollaborateurRepositoryPortAdapteur();
    ProjetRepositoryPortAdapteur projetRepositoryPortAdapteur =
        new ProjetRepositoryPortAdapteur();
    TacheConsole tacheConsole = new TacheConsole(tacheRepositoryPortAdapteur,
        collaborateurRepositoryPortAdapteur, projetRepositoryPortAdapteur);

    //Affichage du sous menu tache
    tacheConsole.choixSousMenuTache();
  }

  /**
   * <p>Methode de choix de la fonctionnalité prolongation.</p>
   */
  private static void choixMenuProlongation() throws Exception {
    //Instanciation de la classe concrete de l'interface ProlongationRepositoryPort
    ProlongationRepositoryPortAdapteur prolongationRepositoryPortAdapteur =
        new ProlongationRepositoryPortAdapteur();
    TacheRepositoryPortAdapteur tacheRepositoryPortAdapteur =
        new TacheRepositoryPortAdapteur();
    ProlongationConsole prolongationConsole = new ProlongationConsole(
        prolongationRepositoryPortAdapteur, tacheRepositoryPortAdapteur);

    //Affichage du sous menu tache
    prolongationConsole.choixSousMenuProlongation();
  }

  private static void choixMenuImprevu() throws Exception {
    //Instanciation de la classe concrete de l'interface ImprevuRepositoryPort
    ImprevuRepositoryPortAdapteur imprevuRepositoryPortAdapteur = new ImprevuRepositoryPortAdapteur();
    TacheRepositoryPortAdapteur tacheRepositoryPortAdapteur = new TacheRepositoryPortAdapteur();
    ImprevuConsole imprevuConsole = new ImprevuConsole(imprevuRepositoryPortAdapteur,
        tacheRepositoryPortAdapteur);

    //Affichage du sous menu tache
    imprevuConsole.choixSousMenuImprevu();
  }

  /**
   * <p>Methode de choix de la fonctionnalité tache.</p>
   */
  private static void choixMenuFinTache() throws Exception {

    //Instanciation de la classe concrete de l'interface TacheRepositoryPort
    TacheRepositoryPortAdapteur tacheRepositoryPortAdapteur =
        new TacheRepositoryPortAdapteur();
    CollaborateurRepositoryPortAdapteur collaborateurRepositoryPortAdapteur =
        new CollaborateurRepositoryPortAdapteur();
    ProjetRepositoryPortAdapteur projetRepositoryPortAdapteur =
        new ProjetRepositoryPortAdapteur();
    FinaliserTacheConsole finaliserTacheConsole = new FinaliserTacheConsole(
        tacheRepositoryPortAdapteur,
        collaborateurRepositoryPortAdapteur, projetRepositoryPortAdapteur);

    //Affichage du sous menu tache
    finaliserTacheConsole.choixSousMenuFinTache();
  }

  /**
   * <p>Methode de choix de la fonctionnalité évaluation.</p>
   */
  private static void choixMenuEvaluation() throws Exception {
    //Instanciation de la classe concrete de l'interface CollaborateurRepositoryPort
    CollaborateurRepositoryPortAdapteur collaborateurRepositoryPortAdapteur =
        new CollaborateurRepositoryPortAdapteur();
    EvaluationConsole evaluationConsole = new EvaluationConsole(
        collaborateurRepositoryPortAdapteur);

    //Affichage du sous menu evaluation
    evaluationConsole.choixSousMenuEvaluation();
  }

}
