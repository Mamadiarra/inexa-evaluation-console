package com.inexa.evaluation.core.evaluation.domaine.entite;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.inexa.evaluation.core.evaluation.application.commande.tache.FinaliserTacheCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.tache.GestionnaireFinaliserTache;
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
 * <p>Test unitaire du gestionnaire de commande {@link GestionnaireFinaliserTache}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-11
 */
@ExtendWith(MockitoExtension.class)
class GestionnaireFinaliserTacheTest {

  @Mock
  TacheRepositoryPort tacheRepositoryPort;
  @Mock
  GestionnaireFinaliserTache gestionnaireFinaliserTache;

  @BeforeEach
  void setUp() {
    this.gestionnaireFinaliserTache = new GestionnaireFinaliserTache(this.tacheRepositoryPort);
  }

  @Test
  @DisplayName("Test de finalisation de tache")
  void execute() throws Exception {
    //Given
    UUID tacheId = UUID.randomUUID();

    FinaliserTacheCommande commande = new FinaliserTacheCommande();
    commande.setTacheId(String.valueOf(tacheId));
    commande.setTempsRealise(10);

    Tache tache = new Tache();

    Mockito.when(this.tacheRepositoryPort.rechercheParId(any(UUID.class)))
        .thenReturn(tache);
    tache.setTempsFinTache(commande.getTempsRealise());
    tache.finaliserTache();

    Mockito.doNothing().when(this.tacheRepositoryPort)
        .finaliserTache(tache);

    //When
    this.gestionnaireFinaliserTache.execute(commande);

    //Then
    Mockito.verify(this.tacheRepositoryPort, times(1))
        .rechercheParId(any(UUID.class));
    Mockito.verify(this.tacheRepositoryPort, times(1))
        .finaliserTache(tache);
  }

}