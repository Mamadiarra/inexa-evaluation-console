package com.inexa.evaluation.core.evaluation.domaine.entite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.inexa.evaluation.core.evaluation.application.commande.projet.CreerProjetCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.projet.GestionnaireCreerProjet;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * <p>Test unitaire du gestionnaire de commande {@link GestionnaireCreerProjet}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-10
 */
@ExtendWith(MockitoExtension.class)
class GestionnaireCreerProjetTest {

  @Mock
  ProjetRepositoryPort projetRepositoryPort;
  GestionnaireCreerProjet gestionnaireCreerProjet;

  @BeforeEach
  void setUp() {
    this.gestionnaireCreerProjet = new GestionnaireCreerProjet(this.projetRepositoryPort);
  }

  @Test
  @DisplayName("Test de création de projet")
  void execute() {
    //Given
    CreerProjetCommande commande = new CreerProjetCommande();
    commande.setNom("PROJET EVALUATION");
    commande.setDescription("Projet d'évaluation des capacités du développeur");

    Mockito.doNothing().when(this.projetRepositoryPort).ajouter(any(Projet.class));

    //When
    this.gestionnaireCreerProjet.execute(commande);

    //Then
    Mockito.verify(this.projetRepositoryPort, times(1))
        .ajouter(any(Projet.class));
  }
}