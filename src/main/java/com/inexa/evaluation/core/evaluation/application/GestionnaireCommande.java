package com.inexa.evaluation.core.evaluation.application;

/**
 * <p>Gestionnaire de console.</p>
 *
 * @author DIARRA MAMADOU 2020-08-03
 */
public interface GestionnaireCommande<T> {
  void execute(T commande) throws Exception;
}
