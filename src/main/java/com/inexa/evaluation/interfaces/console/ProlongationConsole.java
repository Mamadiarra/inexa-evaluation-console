package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.commande.prolongation.CreerProlongationCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.prolongation.GestionnaireCreerProlongation;
import com.inexa.evaluation.core.evaluation.application.gestionnairerequete.GestionnaireProlongation;
import com.inexa.evaluation.core.evaluation.application.port.ProlongationRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Prolongation;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import com.inexa.evaluation.core.evaluation.domaine.objetvaleur.TacheStatut;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

/**
 * <p>Console  de l'entité domaine {@link Prolongation}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class ProlongationConsole {

  private final GestionnaireRequete<UUID, Prolongation> gestionnaireRequeteProlongation;
  private final TacheRepositoryPort tacheRepositoryPort;
  private final GestionnaireCommande<CreerProlongationCommande> gestionnaireCommandeCreerProlongation;


  public ProlongationConsole(ProlongationRepositoryPort prolongationRepositoryPort,
      TacheRepositoryPort tacheRepositoryPort) {
    this.gestionnaireRequeteProlongation = new GestionnaireProlongation(prolongationRepositoryPort);
    this.tacheRepositoryPort = tacheRepositoryPort;
    gestionnaireCommandeCreerProlongation = new GestionnaireCreerProlongation(
        prolongationRepositoryPort, tacheRepositoryPort);
  }

  /**
   * <p>Initialisation du menu.</p>
   */
  private List<String> initialisationSousMenuGeneral() {
    return Arrays
        .asList("Liste des prolongations", "Créer une prolongation", "Retour sur le menu général");
  }

  /**
   * <p>choix du menu.</p>
   */
  public void choixSousMenuProlongation() throws Exception {
    TextIO textIO = new UniversalServiceConsole().initialisationTextio();
    String choix = textIO.newStringInputReader()
        .withNumberedPossibleValues(this.initialisationSousMenuGeneral())
        .read("Choisissez un sous menu de prolongation");
    this.afficherSousMenu(choix);
  }

  /**
   * <p>Methode d'appel de console en fonction du choix du menu.</p>
   */
  private void afficherSousMenu(String choix) throws Exception {
    switch (choix) {
      case "Liste des prolongations":
        this.listeProlongation();
        break;
      case "Créer une prolongation":
        this.creerProlongation();
        break;
      case "Retour sur le menu général":
        this.retourSurMenuGeneral();
        break;
      default:
        System.out.println("Choix non disponible");
    }
  }

  /**
   * Methode d'affichage des prolongations
   */
  private void listeProlongation() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();

    // Récupération des prolongations
    List<Prolongation> listeProlongations = this.gestionnaireRequeteProlongation.lister();

    // Lister les prolongations
    universalServiceConsole.affichageTitre(" LISTE DES PROLONGATIONS ");

    //Debut affichage des prolongations
    universalServiceConsole.afficherTableauTitre("TACHE", "MOTIF", "TEMPS", null);

    if (listeProlongations.isEmpty()) {
      terminal.println("            Aucune prolongation disponible           ");
    } else {
      for (Prolongation prolongation : listeProlongations) {
        String libelleTache = prolongation.getTache().getLibelle();
        terminal.print(libelleTache + "     -     ");
        terminal.print(prolongation.getMotif() + "     -     ");
        terminal.println(String.valueOf(prolongation.getTemps()));
        terminal.println("-------------------------------------------------------");
      }
    }

    terminal.println();
    terminal.println();
    //Fin affichage des prolongations

    // Affichage du menu de la fonctionnalité prolongations
    this.choixSousMenuProlongation();
  }

  /**
   * <p>Methode de création de prolongation.</p>
   */
  private void creerProlongation() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextIO textIO = universalServiceConsole.initialisationTextio();

    TextTerminal<?> terminal = textIO.getTextTerminal();

    //Debut Formulaire de création de prolongation
    universalServiceConsole.afficherFormulaireTitre("Création de prolongation");

    //Recupération de la liste des taches non terminées
    List<String> listeTacheEnCours = this
        .tableauTacheEnCours(this.tacheRepositoryPort.lister());

    String tache = textIO.newStringInputReader()
        .withNumberedPossibleValues(listeTacheEnCours)
        .read("Entrer l'identifiant de la tache à prolonger :");
    String motif = textIO.newStringInputReader()
        .read("Entrer le motif de la prolongation de temps :");
    int temps = textIO.newIntInputReader()
        .read("Entrer le temps de prolongation :");

    //Fin Formulaire de création de prolongation
    universalServiceConsole.afficherMessageTraitementEnCours();

    //Debut d'enregistrement des informations
    CreerProlongationCommande commande = new CreerProlongationCommande();
    commande.setMotif(motif);
    commande.setTemps(temps);
    commande.setTacheId(String.valueOf(universalServiceConsole.recuperationPremierMot(tache)));

    this.gestionnaireCommandeCreerProlongation.execute(commande);

    universalServiceConsole.afficherMessageTraitementTermine();
    //Fin d'enregistremernt des informations

    this.choixSousMenuProlongation();
  }

  private List<String> tableauTacheEnCours(List<Tache> lister) {
    return lister.stream().filter(tache -> tache.getStatut() == TacheStatut.DEMARRER).map(tache -> {
      return tache.getId() + " - " + tache.getLibelle();
    }).collect(Collectors.toList());
  }

  public void retourSurMenuGeneral() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();
    terminal.println();
    MenuGeneraleConsole.choixMenuGeneral();
  }
}
