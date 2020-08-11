package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

/**
 * <p>Console d'évaluation de l'entité domaine {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-06
 */
public class EvaluationConsole extends CollaborateurConsole {

  public EvaluationConsole(
      CollaborateurRepositoryPort collaborateurRepositoryPort) {
    super(collaborateurRepositoryPort);
  }

  /**
   * <p>Initialisation du menu.</p>
   */
  private List<String> initialisationSousMenuGeneral() {
    return Arrays
        .asList("Liste des collaborateurs et leurs notes globales", "Retour sur le menu général");

  }

  /**
   * <p>choix du menu.</p>
   */
  public void choixSousMenuEvaluation() throws Exception {
    TextIO textIO = new UniversalServiceConsole().initialisationTextio();
    String choix = textIO.newStringInputReader()
        .withNumberedPossibleValues(this.initialisationSousMenuGeneral())
        .read("Choisissez une option");
    this.afficherSousMenu(choix);
  }

  /**
   * <p>Methode d'appel de console en fonction du choix du menu.</p>
   */
  private void afficherSousMenu(String choix) throws Exception {
    switch (choix) {
      case "Liste des collaborateurs et leurs notes globales":
        this.listeCollaborateur();
        break;
      case "Retour sur le menu général":
        this.retourSurMenuGeneral();
        break;
      default:
        System.out.println("Choix non disponible");
    }
  }

  /**
   * Methode d'affichage des collaborateurs
   */
  public void listeCollaborateur() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();

    // Récupération des collaborateurs
    List<Collaborateur> listeCollaborateurs = this.gestionnaireRequeteCollaborateur.lister();

    // Lister les collaborateurs
    universalServiceConsole.affichageTitre("LISTE DES COLLABORATEURS ET LEURS NOTES GLOBALES");

    //Debut affichage des collaborateurs
    universalServiceConsole.afficherTableauTitre("Nom et prenom", "Fonction", "Note globale", null);

    if (listeCollaborateurs.isEmpty()) {
      terminal.println("            Aucun collaborateur disponible           ");
    } else {
      for (Collaborateur collaborateur : listeCollaborateurs) {
        terminal.print(collaborateur.getNom() + " - " + collaborateur.getPrenom());
        terminal.print(collaborateur.getFonction() + "     -     ");
        terminal.println(String.valueOf(collaborateur.noteGlobale()));
        terminal.println("-------------------------------------------------------");
      }
    }

    terminal.println();
    terminal.println();
    //Fin affichage des collaborateurs

    // Affichage du menu de la fonctionnalité collaborateur
    this.choixSousMenuEvaluation();
  }

}
