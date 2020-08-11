package com.inexa.evaluation.core.evaluation.application.gestionnairecommande.tache;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.casutilisation.tache.CreerTache;
import com.inexa.evaluation.core.evaluation.application.commande.tache.CreerTacheCommande;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import javax.validation.Valid;

/**
 * <p>Gestionnaire de la commande {@link CreerTacheCommande}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class GestionnaireCreerTache implements GestionnaireCommande<CreerTacheCommande> {

  private final CreerTache creerTache;

  public GestionnaireCreerTache(TacheRepositoryPort tacheRepositoryPort,
      CollaborateurRepositoryPort collaborateurRepositoryPort,
      ProjetRepositoryPort projetRepositoryPort) {
    this.creerTache = new CreerTache(tacheRepositoryPort, collaborateurRepositoryPort,
        projetRepositoryPort);
  }

  @Override
  public void execute(@Valid CreerTacheCommande commande) throws Exception {
    this.creerTache.ajouter(commande);
  }
}
