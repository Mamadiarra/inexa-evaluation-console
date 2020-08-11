package com.inexa.evaluation.core.evaluation.domaine.entite;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.inexa.evaluation.core.evaluation.application.commande.prolongation.CreerProlongationCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.prolongation.GestionnaireCreerProlongation;
import com.inexa.evaluation.core.evaluation.application.port.ProlongationRepositoryPort;
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
 * <p>Test unitaire du gestionnaire de commande {@link GestionnaireCreerProlongation}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-11
 */
@ExtendWith(MockitoExtension.class)
class GestionnaireCreerProlongationTest {

  @Mock
  ProlongationRepositoryPort prolongationRepositoryPort;
  @Mock
  TacheRepositoryPort tacheRepositoryPort;
  @Mock
  GestionnaireCreerProlongation gestionnaireCreerProlongation;

  @BeforeEach
  void setUp() {
    this.gestionnaireCreerProlongation = new GestionnaireCreerProlongation(
        this.prolongationRepositoryPort, this.tacheRepositoryPort);
  }

  @Test
  @DisplayName("Test de création de prolongation")
  void execute() throws Exception {
    //Given
    UUID tacheId = UUID.randomUUID();

    CreerProlongationCommande commande = new CreerProlongationCommande();
    commande.setTacheId(String.valueOf(tacheId));
    commande.setMotif("DOLEANCE");
    commande.setTemps(3);

    Tache tache = new Tache();
    tache.setId(tacheId);
    //Prolongation prolongation = new Prolongation(commande.getMotif(), commande.getTemps(), tache);

    Mockito.when(this.tacheRepositoryPort.rechercheParId(any(UUID.class))).thenReturn(tache);
    Mockito.doNothing().when(this.prolongationRepositoryPort).ajouter(any(Prolongation.class));

    /*
    Mockito.doNothing().when(this.tacheRepositoryPort)
        .ajouterProlongation(tache.getId(), prolongation);
    */
    //When
    this.gestionnaireCreerProlongation.execute(commande);

    //Then
    Mockito.verify(this.tacheRepositoryPort, times(1))
        .rechercheParId(any(UUID.class));
    Mockito.verify(this.prolongationRepositoryPort, times(1))
        .ajouter(any(Prolongation.class));
    /*
    Mockito.verify(this.tacheRepositoryPort, times(1))
        .ajouterProlongation(tacheId, prolongation);

     */
  }
}