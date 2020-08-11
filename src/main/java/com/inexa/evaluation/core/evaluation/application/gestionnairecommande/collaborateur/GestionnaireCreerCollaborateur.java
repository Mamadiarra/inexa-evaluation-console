package com.inexa.evaluation.core.evaluation.application.gestionnairecommande.collaborateur;

import com.inexa.evaluation.core.evaluation.application.GestionnaireCommande;
import com.inexa.evaluation.core.evaluation.application.casutilisation.collaborateur.CreerCollaborateur;
import com.inexa.evaluation.core.evaluation.application.commande.collaborateur.CreerCollaborateurCommande;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import javax.validation.Valid;

/**
 * <p>Gestionnaire de la commande {@link CreerCollaborateurCommande}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-04
 */
public class GestionnaireCreerCollaborateur implements GestionnaireCommande<CreerCollaborateurCommande> {

  private final CreerCollaborateur creerCollaborateur;

  public GestionnaireCreerCollaborateur(CollaborateurRepositoryPort collaborateurRepositoryPort) {
    this.creerCollaborateur = new CreerCollaborateur(collaborateurRepositoryPort);
  }

  @Override
  public void execute(@Valid CreerCollaborateurCommande commande) {
    this.creerCollaborateur.ajouter(commande);
  }
}
