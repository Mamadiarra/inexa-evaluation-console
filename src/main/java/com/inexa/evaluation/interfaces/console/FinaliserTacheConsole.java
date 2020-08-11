package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.commande.tache.FinaliserTacheCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.tache.GestionnaireFinaliserTache;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import com.inexa.evaluation.core.evaluation.domaine.objetvaleur.TacheStatut;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

/**
 * <p>Console de finalisation de l'entité domaine {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-06
 */
public class FinaliserTacheConsole extends TacheConsole {

  private final TacheRepositoryPort tacheRepositoryPort;
  private final GestionnaireCommande<FinaliserTacheCommande> gestionnaireCommandeFinaliserTache;

  public FinaliserTacheConsole(
      TacheRepositoryPort tacheRepositoryPort,
      CollaborateurRepositoryPort collaborateurRepositoryPort,
      ProjetRepositoryPort projetRepositoryPort) {
    super(tacheRepositoryPort, collaborateurRepositoryPort, projetRepositoryPort);
    this.tacheRepositoryPort = tacheRepositoryPort;
    this.gestionnaireCommandeFinaliserTache = new GestionnaireFinaliserTache(tacheRepositoryPort);
  }

  /**
   * <p>Initialisation du menu.</p>
   */
  private List<String> initialisationSousMenuGeneral() {
    return Arrays.asList("Liste des taches", "Finaliser une tache", "Retour sur le menu général");
  }

  /**
   * <p>choix du menu.</p>
   */
  public void choixSousMenuFinTache() throws Exception {
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
      case "Liste des taches":
        this.listeTache();
        break;
      case "Finaliser une tache":
        this.finaliserTache();
        break;
      case "Retour sur le menu général":
        this.retourSurMenuGeneral();
        break;
      default:
        System.out.println("Choix non disponible");
    }
  }

  /**
   * Methode d'affichage des taches
   */
  protected void listeTache() throws Exception {
    this.affichageListeTache();
    // Affichage du menu de la fonctionnalité taches
    this.choixSousMenuFinTache();
  }

  /**
   * <p>Methode de finalisation de tache.</p>
   */
  private void finaliserTache() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();
    TextIO textIO = new UniversalServiceConsole().initialisationTextio();

    //Debut Formulaire de finalisation de tache
    universalServiceConsole.afficherFormulaireTitre("Finalisation de tache");

    //Recupération de la liste des taches
    List<String> listeTachesEnCours = this
        .tableauTacheEncours(this.tacheRepositoryPort.lister());

    String tache = textIO.newStringInputReader()
        .withNumberedPossibleValues(listeTachesEnCours)
        .read("Entrer l'identifiant de la tache à finaliser :");
    int tempsRealise = textIO.newIntInputReader()
        .read("Entrer temps réalisé sur la tache :");

    //Fin Formulaire de finalisation de tache
    universalServiceConsole.afficherMessageTraitementEnCours();

    //Debut d'enregistrement des informations
    FinaliserTacheCommande finaliserTacheCommande = new FinaliserTacheCommande();
    finaliserTacheCommande.setTacheId(String.valueOf(universalServiceConsole.recuperationPremierMot(tache)));
    finaliserTacheCommande.setTempsRealise(tempsRealise);

    try {
      this.gestionnaireCommandeFinaliserTache.execute(finaliserTacheCommande);
      universalServiceConsole.afficherMessageTraitementTermine();
      //Fin d'enregistrement des informations
    } catch (Exception e) {
      terminal.println();
      terminal.println(e.getMessage());
      terminal.println();
    }

    this.choixSousMenuFinTache();
  }

  private List<String> tableauTacheEncours(List<Tache> lister) {
    return lister.stream().filter(tache -> {
      return tache.getStatut() == TacheStatut.DEMARRER;
    }).map(tache -> {
      return tache.getId() + " - " + tache.getLibelle();
    }).collect(Collectors.toList());
  }

}
