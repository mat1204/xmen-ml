package com.ml.xmen.xmenml.comprobadores;

/**
 * Interfaz utilizada para abstraer el comportamiento de Comprobador de ADN
 */
public interface ComprobadorADN {

    /**
     * Indica si la Cadena posee una coincidencia con patron de Gen Mutante
     * @param cadenaADN cadena de ADN a analizar
     * @return
     */
    public Boolean contieneSecuenciaMutante(String cadenaADN);

    /**
     * Indica si se posee el Gen Mutante a partir de un numero de coincidencias de Gen Mutante
     * @param numeroCoincidencias coincidencias de gen mutante a utilizar
     * @return
     */
    public Boolean poseeGenMutante(Integer numeroCoincidencias);

    /**
     * Indica si la cadena es valida para comprobar la presencia de coincidencia de gen Mutante
     * @param cadenaADN cadena de ADN a analizar
     * @return
     */
    public Boolean esADNComprobable(String cadenaADN);
}
