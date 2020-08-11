package com.inexa.evaluation.interfaces.console;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.GestionnaireRequete;
import com.inexa.evaluation.core.evaluation.application.commande.imprevu.CreerImprevuCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.imprevu.GestionnaireCreerImprevu;
import com.inexa.evaluation.core.evaluation.application.gestionnairerequete.GestionnaireImprevu;
import com.inexa.evaluation.core.evaluation.application.port.ImprevuRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import com.inexa.evaluation.core.evaluation.domaine.objetvaleur.TacheStatut;
import com.inexa.evaluation.interfaces.service.UniversalServiceConsole;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 * <p>Console  de l'entité domaine {@link Imprevu}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-05
 */
public class ImprevuConsole {

  private final GestionnaireRequete<UUID, Imprevu> gestionnaireRequeteImprevu;
  private final TacheRepositoryPort tacheRepositoryPort;
  private final GestionnaireCommande<CreerImprevuCommande> gestionnaireCommandeCreerImprevu;

  public ImprevuConsole(ImprevuRepositoryPort imprevuRepositoryPort,
      TacheRepositoryPort tacheRepositoryPort) {
    this.gestionnaireRequeteImprevu = new GestionnaireImprevu(imprevuRepositoryPort);
    this.tacheRepositoryPort = tacheRepositoryPort;
    gestionnaireCommandeCreerImprevu = new GestionnaireCreerImprevu(
        imprevuRepositoryPort, tacheRepositoryPort);
  }

  /**
   * <p>Initialisation du menu.</p>
   */
  private List<String> initialisationSousMenuGeneral() {
    return Arrays.asList("Liste des imprevus", "Créer un imprevu", "Retour sur le menu général");
  }

  /**
   * <p>choix du menu.</p>
   */
  public void choixSousMenuImprevu() throws Exception {
    TextIO textIO = new UniversalServiceConsole().initialisationTextio();
    String choix = textIO.newStringInputReader()
        .withNumberedPossibleValues(this.initialisationSousMenuGeneral())
        .read("Choisissez un sous menu d'imprévu");
    this.afficherSousMenu(choix);
  }

  /**
   * <p>Methode d'appel de console en fonction du choix du menu.</p>
   */
  private void afficherSousMenu(String choix) throws Exception {
    switch (choix) {
      case "Liste des imprevus":
        this.listeImprevu();
        break;
      case "Créer un imprevu":
        this.creerImprevu();
        break;
      case "Retour sur le menu général":
        this.retourSurMenuGeneral();
        break;
      default:
        System.out.println("Choix non disponible");
    }
  }

  /**
   * Methode d'affichage des imprévus
   */
  private void listeImprevu() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextTerminal<?> terminal = universalServiceConsole.initialisationTerminal();

    // Récupération des imprévus
    List<Imprevu> listeImprevus = this.gestionnaireRequeteImprevu.lister();

    // Lister les imprévus
    universalServiceConsole.affichageTitre("LISTE DES IMPREVUS");

    //Debut affichage des imprévus
    universalServiceConsole.afficherTableauTitre("TACHE", "DESCRIPTION", "TEMPS", null);

    if (listeImprevus.isEmpty()) {
      terminal.println("            Aucun imprévu disponible           ");
    } else {
      for (Imprevu imprevu : listeImprevus) {
        String libelleTache = imprevu.getTache().getLibelle();
        terminal.print(libelleTache + "     -     ");
        terminal.print(imprevu.getMotif() + "     -     ");
        terminal.println(String.valueOf(imprevu.getTemps()));
        terminal.println("-------------------------------------------------------");
      }
    }

    terminal.println();
    terminal.println();
    //Fin affichage des imprévus

    // Affichage du menu de la fonctionnalité imprévus
    this.choixSousMenuImprevu();
  }

  /**
   * <p>Methode de création d'imprévu.</p>
   */
  private void creerImprevu() throws Exception {
    UniversalServiceConsole universalServiceConsole = new UniversalServiceConsole();
    TextIO textIO = TextIoFactory.getTextIO();

    //Debut Formulaire de création d'imprévu
    universalServiceConsole.afficherFormulaireTitre("Création d'imprévu");

    //Recupération de la liste des taches non terminées
    List<String> listeTacheEnCours = this
        .tableauTacheEnCours(this.tacheRepositoryPort.lister());

    String tache = textIO.newStringInputReader()
        .withNumberedPossibleValues(listeTacheEnCours)
        .read("Entrer l'identifiant de la tache à prolonger :");
    String motif = textIO.newStringInputReader()
        .read("Entrer la description de l'imprévu :");
    int temps = textIO.newIntInputReader()
        .read("Entrer la durée de l'imprévu :");

    //Fin Formulaire de création d'imprévu
    universalServiceConsole.afficherMessageTraitementEnCours();

    //Debut d'enregistrement des informations
    CreerImprevuCommande commande = new CreerImprevuCommande();
    commande.setMotif(motif);
    commande.setTemps(temps);
    commande.setTacheId(String.valueOf(universalServiceConsole.recuperationPremierMot(tache)));

    this.gestionnaireCommandeCreerImprevu.execute(commande);

    universalServiceConsole.afficherMessageTraitementTermine();
    //Fin d'enregistremernt des informations

    this.choixSousMenuImprevu();
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
