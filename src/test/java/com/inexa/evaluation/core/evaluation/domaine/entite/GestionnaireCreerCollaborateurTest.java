package com.inexa.evaluation.core.evaluation.domaine.entite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.inexa.evaluation.core.evaluation.application.commande.collaborateur.CreerCollaborateurCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.collaborateur.GestionnaireCreerCollaborateur;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * <p>Test unitaire du gestionnaire de commande {@link GestionnaireCreerCollaborateur}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-10
 */
@ExtendWith(MockitoExtension.class)
class GestionnaireCreerCollaborateurTest {

  @Mock
  CollaborateurRepositoryPort collaborateurRepositoryPort;
  GestionnaireCreerCollaborateur gestionnaireCreerCollaborateur;

  @BeforeEach
  void setUp() {
    this.gestionnaireCreerCollaborateur = new GestionnaireCreerCollaborateur(this.collaborateurRepositoryPort);
  }

  @Test
  @DisplayName("Test de création de collaborateur")
  void execute() {
    //Given
    CreerCollaborateurCommande commande = new CreerCollaborateurCommande();
    commande.setNom("Diarra");
    commande.setPrenom("Mamadou");
    commande.setFonction("Java junior");

    Mockito.doNothing().when(this.collaborateurRepositoryPort).ajouter(any(Collaborateur.class));

    //When
    this.gestionnaireCreerCollaborateur.execute(commande);

    //Then
    Mockito.verify(this.collaborateurRepositoryPort, times(1))
        .ajouter(any(Collaborateur.class));
  }

}