package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.commande.collaborateur.CreerCollaborateurCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.collaborateur.GestionnaireCreerCollaborateur;
import com.inexa.evaluation.core.evaluation.application.gestionnairerequete.GestionnaireCollaborateur;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

/**
 * <p>Console  de l'entité domaine {@link Collaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public class CollaborateurConsole {

  protected final GestionnaireRequete<UUID, Collaborateur> gestionnaireRequeteCollaborateur;
  private final GestionnaireCommande<CreerCollaborateurCommande> gestionnaireCreerCollaborateur;

  public CollaborateurConsole(CollaborateurRepositoryPort collaborateurRepositoryPort) {
    this.gestionnaireRequeteCollaborateur = new GestionnaireCollaborateur(
        collaborateurRepositoryPort);
    this.gestionnaireCreerCollaborateur = new GestionnaireCreerCollaborateur(
        collaborateurRepositoryPort);
  }

  /**
   * <p>Initialisation du menu.</p>
   */
  private List<String> initialisationSousMenuGeneral() {
    return Arrays
        .asList("Liste des collaborateurs", "Créer un collaborateur", "Retour sur le menu général");
  }

  /**
   * <p>choix du menu.</p>
   */
  public void choixSousMenuCollaborateur() throws Exception {
    TextIO textIO = new UniversalServiceConsole().initialisationTextio();
    String choix = textIO.newStringInputReader()
        .withNumberedPossibleValues(this.initialisationSousMenuGeneral())
        .read("Choisissez un sous menu de collaborateur");
    this.afficherSousMenu(choix);
  }

  /**
   * <p>Methode d'appel de console en fonction du choix du menu.</p>
   */
  private void afficherSousMenu(String choix) throws Exception {
    switch (choix) {
      case "Liste des collaborateurs":
        this.listeCollaborateur();
        break;
      case "Créer un collaborateur":
        this.creerCollaborateur();
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
    this.affichageListe();
    // Affichage du menu de la fonctionnalité collaborateur
    this.choixSousMenuCollaborateur();
  }

  protected void affichageListe() {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();

    // Récupération des collaborateurs
    List<Collaborateur> listeCollaborateurs = this.gestionnaireRequeteCollaborateur.lister();

    // Lister les collaborateurs
    universalServiceConsole.affichageTitre("LISTE DES COLLABORATEURS");

    //Debut affichage des collaborateurs
    universalServiceConsole.afficherTableauTitre("Nom", "Prenom", "Fonction", null);

    if (listeCollaborateurs.isEmpty()) {
      terminal.println("            Aucun collaborateur disponible           ");
    } else {
      for (Collaborateur collaborateur : listeCollaborateurs) {
        terminal.print(collaborateur.getNom() + "     -     ");
        terminal.print(collaborateur.getPrenom() + "     -     ");
        terminal.println(collaborateur.getFonction());
        terminal.println("-------------------------------------------------------");
      }
    }

    terminal.println();
    terminal.println();
    //Fin affichage des collaborateurs
  }

  public void creerCollaborateur() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextIO textIO = universalServiceConsole.initialisationTextio();

    //Debut Formulaire de création de collaborateur
    universalServiceConsole.afficherFormulaireTitre("Création de collaborateur");

    String nom = textIO.newStringInputReader()
        .read("Entrer le nom du collaborateur :");
    String prenom = textIO.newStringInputReader()
        .read("Entrer le prenom du collaborateur :");
    String fonction = textIO.newStringInputReader()
        .read("Entrer la fonction du collaborateur :");

    //Fin Formulaire de création de collaborateur
    universalServiceConsole.afficherMessageTraitementEnCours();

    //Debut d'enregistrement des informations
    CreerCollaborateurCommande creerCollaborateurCommande = new CreerCollaborateurCommande();
    creerCollaborateurCommande.setNom(nom);
    creerCollaborateurCommande.setPrenom(prenom);
    creerCollaborateurCommande.setFonction(fonction);

    this.gestionnaireCreerCollaborateur.execute(creerCollaborateurCommande);

    universalServiceConsole.afficherMessageTraitementTermine();
    //Fin d'enregistremernt des informations

    this.choixSousMenuCollaborateur();
  }

  public void retourSurMenuGeneral() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();
    terminal.println();
    MenuGeneraleConsole.choixMenuGeneral();
  }

}
