package com.inexa.evaluation.core.evaluation.application.casutilisation.imprevu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import com.inexa.evaluation.core.evaluation.application.commande.imprevu.CreerImprevuCommande;
import com.inexa.evaluation.core.evaluation.application.gestionnairecommande.imprevu.GestionnaireCreerImprevu;
import com.inexa.evaluation.core.evaluation.application.port.ImprevuRepositoryPort;
import com.inexa.evaluation.core.evaluation.application.port.TacheRepositoryPort;
import com.inexa.evaluation.core.evaluation.domaine.entite.Imprevu;
import com.inexa.evaluation.core.evaluation.domaine.entite.Tache;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * <p>Test unitaire du gestionnaire de commande {@link GestionnaireCreerImprevu}.</p>
 *
 * @author DIARRA MAMADOU 2020-08-11
 */
@ExtendWith(MockitoExtension.class)
class GestionnaireCreerImprevuTest {

  @Mock
  ImprevuRepositoryPort imprevuRepositoryPort;
  @Mock
  TacheRepositoryPort tacheRepositoryPort;
  @Mock
  GestionnaireCreerImprevu gestionnaireCreerImprevu;

  @BeforeEach
  void setUp() {
    this.gestionnaireCreerImprevu = new GestionnaireCreerImprevu(
        this.imprevuRepositoryPort, this.tacheRepositoryPort);
  }

  @Test
  @DisplayName("Test de création d'imprévu")
  void execute() throws Exception {
    //Given
    UUID tacheId = UUID.randomUUID();

    CreerImprevuCommande commande = new CreerImprevuCommande();
    commande.setTacheId(String.valueOf(tacheId));
    commande.setMotif("DOLEANCE");
    commande.setTemps(3);

    Tache tache = new Tache();
    tache.setId(tacheId);
    //Prolongation prolongation = new Prolongation(commande.getMotif(), commande.getTemps(), tache);

    Mockito.when(this.tacheRepositoryPort.rechercheParId(any(UUID.class))).thenReturn(tache);
    Mockito.doNothing().when(this.imprevuRepositoryPort).ajouter(any(Imprevu.class));

    /*
    Mockito.doNothing().when(this.tacheRepositoryPort)
        .ajouterProlongation(tache.getId(), prolongation);
    */
    //When
    this.gestionnaireCreerImprevu.execute(commande);

    //Then
    Mockito.verify(this.tacheRepositoryPort, times(1))
        .rechercheParId(any(UUID.class));
    Mockito.verify(this.imprevuRepositoryPort, times(1))
        .ajouter(any(Imprevu.class));
    /*
    Mockito.verify(this.tacheRepositoryPort, times(1))
        .ajouterProlongation(tacheId, prolongation);

     */
  }

}