package com.ml.xmen.xmenml.services;

public interface MutantService {

    /**
     * Indica si la candenas de ADN contiene el gen Mutante
     * @param cadenaADN Array de cadenas de ADN a verificar
     * @return booleano, true indicando si posee el gen mutante
     */
    Boolean isMutant(String[] cadenaADN);
}
