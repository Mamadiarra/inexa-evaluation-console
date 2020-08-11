package com.inexa.evaluation.core.evaluation.application.gestionnairecommande.projet;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.casutilisation.projet.CreerProjet;
import com.inexa.evaluation.core.evaluation.application.commande.projet.CreerProjetCommande;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import javax.validation.Valid;

/**
 * <p>Gestionnaire de la commande {@link CreerProjetCommande}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class GestionnaireCreerProjet implements GestionnaireCommande<CreerProjetCommande> {

  private final CreerProjet creerProjet;

  public GestionnaireCreerProjet(ProjetRepositoryPort projetRepositoryPort) {
    this.creerProjet = new CreerProjet(projetRepositoryPort);
  }

  @Override
  public void execute(@Valid CreerProjetCommande commande) {
    this.creerProjet.ajouter(commande);
  }
}
