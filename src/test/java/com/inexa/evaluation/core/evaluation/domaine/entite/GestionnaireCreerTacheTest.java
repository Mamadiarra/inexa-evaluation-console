package com.inexa.evaluation.core.evaluation.domaine.entite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.inexa.evaluation.core.evaluation.application.commande.tache.CreerTacheCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.tache.GestionnaireCreerTache;
import com.inexa.evaluation.core.evaluation.application.port.CollaborateurRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.ProjetRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * <p>Test unitaire du gestionnaire de commande {@link GestionnaireCreerTache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-10
 */
@ExtendWith(MockitoExtension.class)
class GestionnaireCreerTacheTest {

  @Mock
  TacheRepositoryPort tacheRepositoryPort;
  @Mock
  CollaborateurRepositoryPort collaborateurRepositoryPort;
  @Mock
  ProjetRepositoryPort projetRepositoryPort;
  @Mock
  GestionnaireCreerTache gestionnaireCreerTache;

  @BeforeEach
  void setUp() {
    this.gestionnaireCreerTache = new GestionnaireCreerTache(this.tacheRepositoryPort,
        this.collaborateurRepositoryPort, this.projetRepositoryPort);
  }

  @Test
  @DisplayName("Test de création de tache")
  void execute() throws Exception {

    //Given
    CreerTacheCommande commande = new CreerTacheCommande();
    UUID collaborateurId = UUID.randomUUID();
    UUID projetId = UUID.randomUUID();
    commande.setCollaborateurId(String.valueOf(collaborateurId));
    commande.setProjetId(String.valueOf(projetId));
    commande.setEstimation(8);
    commande.setLibelle("CRUD PAYS");

    Collaborateur collaborateur = Mockito.mock(Collaborateur.class);
    Projet projet = Mockito.mock(Projet.class);

    Tache tacheAjouter = new Tache();

    Mockito.when(this.collaborateurRepositoryPort.rechercherParId(any(UUID.class)))
        .thenReturn(collaborateur);
    Mockito.when(this.projetRepositoryPort.rechercherParId(any(UUID.class)))
        .thenReturn(projet);
    Mockito.when(this.tacheRepositoryPort.ajouter(any(Tache.class)))
        .thenReturn(tacheAjouter);
    Mockito.doNothing().when(this.collaborateurRepositoryPort)
        .ajouterTache(tacheAjouter.getId(), tacheAjouter);
    Mockito.doNothing().when(this.projetRepositoryPort)
        .ajouterTache(tacheAjouter.getId(), tacheAjouter);

    //When
    this.gestionnaireCreerTache.execute(commande);

    //Then
    Mockito.verify(this.collaborateurRepositoryPort, times(1))
        .rechercherParId(any(UUID.class));
    Mockito.verify(this.projetRepositoryPort, times(1))
        .rechercherParId(any(UUID.class));
    Mockito.verify(this.tacheRepositoryPort, times(1))
        .ajouter(any(Tache.class));
    Mockito.verify(this.collaborateurRepositoryPort, times(1))
        .ajouterTache(tacheAjouter.getId(), tacheAjouter);
    Mockito.verify(this.projetRepositoryPort, times(1))
        .ajouterTache(tacheAjouter.getId(), tacheAjouter);
  }
}