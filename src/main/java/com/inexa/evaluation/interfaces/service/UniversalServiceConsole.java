package com.inexa.evaluation.interfaces.service;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 * <p>Service universel pour toutes les consoles.</p>
 *
 * @author DIARRA MAMADOU 2020-08-10
 */
public class UniversalServiceConsole {

  public TextIO initialisationTextio() {
    return TextIoFactory.getTextIO();
  }

  public TextTerminal<?> initialisationTerminal() {
    TextIO textIO = this.initialisationTextio();
    return textIO.getTextTerminal();
  }

  public void affichageTitre(String titre) {
    TextTerminal<?> terminal = this.initialisationTerminal();
    terminal.println("############################");
    terminal.println("#  "+titre+"  #");
    terminal.println("############################");
  }

  public void afficherTableauTitre(String item1, String item2, String item3, String item4) {
    String textAfficher = "";

    if (item1 != null) { textAfficher += "  "+item1+"   "; }
    if (item2 != null) { textAfficher += "  "+item2+"   "; }
    if (item3 != null) { textAfficher += "  "+item3+"   "; }
    if (item4 != null) { textAfficher += "  "+item4+"   "; }

    TextTerminal<?> terminal = this.initialisationTerminal();
    terminal.println("-------------------------------------------------------");
    terminal.println(textAfficher);
    terminal.println("-------------------------------------------------------");
  }

  public void afficherFormulaireTitre(String titre) {
    TextTerminal<?> terminal = this.initialisationTerminal();
    terminal.println("-------------------------------------------------------");
    terminal.println("                "+titre+"              ");
    terminal.println("-------------------------------------------------------");
    terminal.println();
  }

  public void afficherMessageTraitementEnCours() {
    TextTerminal<?> terminal = this.initialisationTerminal();
    terminal.println();
    terminal.println("------------- Enregistrement des informations en cours -----------");
  }

  public void afficherMessageTraitementTermine() {
    TextTerminal<?> terminal = this.initialisationTerminal();
    terminal.println();
    terminal.println("------------- Enregistrement des informations terminé -----------");
    terminal.println();
  }

  public String recuperationPremierMot(String string) {
    return (string + " ").split(" ")[0];
  }

}
