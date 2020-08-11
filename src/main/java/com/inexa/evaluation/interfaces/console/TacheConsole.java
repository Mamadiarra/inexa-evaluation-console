package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.commande.tache.CreerTacheCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.tache.GestionnaireCreerTache;
import com.inexa.evaluation.core.evaluation.application.gestionnairerequete.GestionnaireTache;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Collaborateur;
import com.inexa.evaluation.core.evaluation.domaine.entite.Projet;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 * <p>Console  de l'entité domaine {@link Tache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class TacheConsole {

  private final GestionnaireRequete<UUID, Tache> gestionnaireRequeteTache;
  private final CollaborateurRepositoryPort collaborateurRepositoryPort;
  private final ProjetRepositoryPort projetRepositoryPort;
  private final GestionnaireCommande<CreerTacheCommande> gestionnaireCommandeCreerTache;

  public TacheConsole(TacheRepositoryPort tacheRepositoryPort,
      CollaborateurRepositoryPort collaborateurRepositoryPort,
      ProjetRepositoryPort projetRepositoryPort) {
    gestionnaireRequeteTache = new GestionnaireTache(tacheRepositoryPort);
    this.collaborateurRepositoryPort = collaborateurRepositoryPort;
    this.projetRepositoryPort = projetRepositoryPort;
    gestionnaireCommandeCreerTache = new GestionnaireCreerTache(tacheRepositoryPort,
        collaborateurRepositoryPort, projetRepositoryPort);
  }

  /**
   * <p>Initialisation du menu.</p>
   */
  private List<String> initialisationSousMenuGeneral() {
    return Arrays.asList("Liste des taches", "Créer une tache", "Retour sur le menu général");
  }

  /**
   * <p>choix du menu.</p>
   */
  public void choixSousMenuTache() throws Exception {
    TextIO textIO = new UniversalServiceConsole().initialisationTextio();
    String choix = textIO.newStringInputReader()
        .withNumberedPossibleValues(this.initialisationSousMenuGeneral())
        .read("Choisissez un sous menu de tache");
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
      case "Créer une tache":
        this.creerTache();
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
    this.choixSousMenuTache();
  }

  protected void affichageListeTache() {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();

    // Récupération des taches
    List<Tache> listeTaches = this.gestionnaireRequeteTache.lister();

    // Lister les projets
    universalServiceConsole.affichageTitre("LISTE DES TACHES");

    //Debut affichage des taches
    universalServiceConsole.afficherTableauTitre("Projet", "Collaborateur", "Libellé", "Statut");

    if (listeTaches.isEmpty()) {
      terminal.println("                   Aucun tache disponible                   ");
    } else {
      for (Tache tache : listeTaches) {
        String projet = tache.getProjet().getNom();
        String collaborateur =
            tache.getCollaborateur().getNom() + " " + tache.getCollaborateur().getPrenom();
        String libelle = tache.getLibelle();
        String statut = String.valueOf(tache.getStatut());

        terminal.print(projet + " - ");
        terminal.print(collaborateur + " - ");
        terminal.print(libelle + " - ");
        terminal.println(statut);
        terminal.println("-------------------------------------------------------");
      }
    }

    terminal.println();
    terminal.println();
    //Fin affichage des taches
  }

  /**
   * <p>Methode de création de tache.</p>
   */
  private void creerTache() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextIO textIO = universalServiceConsole.initialisationTextio();

    //Debut Formulaire de création de tache
    universalServiceConsole.afficherFormulaireTitre("Création de tache");

    //Recupération de la liste des collaborateurs
    List<String> listCollaborateurs = this
        .tableauCollaborateurs(this.collaborateurRepositoryPort.lister());
    //Recupération de la liste des projets
    List<String> listProjets = this
        .tableauProjets(this.projetRepositoryPort.lister());

    String libelle = textIO.newStringInputReader()
        .read("Entrer le libellé de la tache :");
    int estimation = textIO.newIntInputReader()
        .read("Entrer l'estimation de la tache (en heure) :");
    String collaborateur = textIO.newStringInputReader()
        .withNumberedPossibleValues(listCollaborateurs)
        .read("Entrer l'identifiant du collaborateur à qui la tache sera attribué :");
    String projet = textIO.newStringInputReader()
        .withNumberedPossibleValues(listProjets)
        .read("Entrer l'identifiant du projet à qui la tache sera assigné :");

    //Fin Formulaire de création de tache
    universalServiceConsole.afficherMessageTraitementEnCours();

    //Debut d'enregistrement des informations
    CreerTacheCommande creerTacheCommande = new CreerTacheCommande();
    creerTacheCommande.setLibelle(libelle);
    creerTacheCommande.setEstimation(estimation);
    creerTacheCommande.setCollaborateurId(String.valueOf(this.premierMot(collaborateur)));
    creerTacheCommande.setProjetId(String.valueOf(this.premierMot(projet)));

    try {
      this.gestionnaireCommandeCreerTache.execute(creerTacheCommande);
      universalServiceConsole.afficherMessageTraitementTermine();
      //Fin d'enregistrement des informations
    } catch (Exception e) {
      TextTerminal<?> terminal = new UniversalServiceConsole().initialisationTerminal();
      terminal.println();
      terminal.println(e.getMessage());
      terminal.println();
    }

    this.choixSousMenuTache();
  }

  private List<String> tableauCollaborateurs(
      List<Collaborateur> listeCollaborateur) {
    return listeCollaborateur.stream().map(collaborateur -> {
      return collaborateur.getId() + " - " + collaborateur.getNom() + " - " + collaborateur
          .getPrenom();
    }).collect(Collectors.toList());
  }

  private List<String> tableauProjets(
      List<Projet> listeProjet) {
    return listeProjet.stream().map(projet -> {
      return projet.getId() + " - " + projet.getNom();
    }).collect(Collectors.toList());
  }

  protected String premierMot(String string) {
    return (string + " ").split(" ")[0];
  }

  public void retourSurMenuGeneral() throws Exception {
    TextIO textIO = TextIoFactory.getTextIO();
    TextTerminal<?> terminal = textIO.getTextTerminal();
    terminal.println();
    MenuGeneraleConsole.choixMenuGeneral();
  }
}
