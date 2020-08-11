package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.commande.projet.CreerProjetCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.projet.GestionnaireCreerProjet;
import com.inexa.evaluation.core.evaluation.application.gestionnairerequete.GestionnaireProjet;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 * <p>Console  de l'entité domaine {@link Projet}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class ProjetConsole {

  private final GestionnaireRequete<UUID, Projet> gestionnaireRequeteProjet;
  private final GestionnaireCommande<CreerProjetCommande> gestionnaireCommandeCreerProjet;

  public ProjetConsole(ProjetRepositoryPort projetRepositoryPort) {
    this.gestionnaireRequeteProjet = new GestionnaireProjet(projetRepositoryPort);
    this.gestionnaireCommandeCreerProjet = new GestionnaireCreerProjet(projetRepositoryPort);
  }

  /**
   * <p>Initialisation du menu.</p>
   */
  private List<String> initialisationSousMenuGeneral() {
    return Arrays.asList("Liste des projets", "Créer un projet", "Retour sur le menu général");
  }

  /**
   * <p>choix du menu.</p>
   */
  public void choixSousMenuProjet() throws Exception {
    TextIO textIO = new UniversalServiceConsole().initialisationTextio();
    String choix = textIO.newStringInputReader()
        .withNumberedPossibleValues(this.initialisationSousMenuGeneral())
        .read("Choisissez un sous menu de projet");
    this.afficherSousMenu(choix);
  }

  /**
   * <p>Methode d'appel de console en fonction du choix du menu.</p>
   */
  private void afficherSousMenu(String choix) throws Exception {
    switch (choix) {
      case "Liste des projets":
        this.listeProjet();
        break;
      case "Créer un projet":
        this.creerProjet();
        break;
      case "Retour sur le menu général":
        this.retourSurMenuGeneral();
        break;
      default:
        System.out.println("Choix non disponible");
    }
  }

  /**
   * Methode d'affichage des projets
   */
  private void listeProjet() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();

    // Récupération des projets
    List<Projet> listeProjets = this.gestionnaireRequeteProjet.lister();

    // Lister les projets
    universalServiceConsole.affichageTitre(" LISTE DES PROJETS ");

    //Debut affichage des projets
    universalServiceConsole.afficherTableauTitre("Nom", "DESCRIPTION", null, null);

    if (listeProjets.isEmpty()) {
      terminal.println("            Aucun projet disponible           ");
    } else {
      for (Projet projet : listeProjets) {
        terminal.print(projet.getNom() + "              -     ");
        terminal.println(projet.getDescription());
        terminal.println("-------------------------------------------------------");
      }
    }

    terminal.println();
    terminal.println();
    //Fin affichage des projets

    // Affichage du menu de la fonctionnalité projets
    this.choixSousMenuProjet();
  }

  /**
   * <p>Methode de création de projet.</p>
   */
  private void creerProjet() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextIO textIO = universalServiceConsole.initialisationTextio();

    //Debut Formulaire de création de projet
    universalServiceConsole.afficherFormulaireTitre("Création de projet");

    String nom = textIO.newStringInputReader()
        .read("Entrer le nom du projet :");
    String description = textIO.newStringInputReader()
        .read("Entrer la description du projet :");

    //Fin Formulaire de création de projet
    universalServiceConsole.afficherMessageTraitementEnCours();

    //Debut d'enregistrement des informations
    CreerProjetCommande creerProjetCommande = new CreerProjetCommande();
    creerProjetCommande.setNom(nom);
    creerProjetCommande.setDescription(description);

    this.gestionnaireCommandeCreerProjet.execute(creerProjetCommande);

    universalServiceConsole.afficherMessageTraitementTermine();
    //Fin d'enregistremernt des informations

    this.choixSousMenuProjet();
  }

  public void retourSurMenuGeneral() throws Exception {
    TextIO textIO = TextIoFactory.getTextIO();
    TextTerminal<?> terminal = textIO.getTextTerminal();
    terminal.println();
    MenuGeneraleConsole.choixMenuGeneral();
  }

}
