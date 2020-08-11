package com.inexa.evaluation.core.evaluation.application;

import java.util.List;

/**
 * <p>Gestionnaire de requete.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public interface GestionnaireRequete<C,R> {
  List<R> lister();
}
