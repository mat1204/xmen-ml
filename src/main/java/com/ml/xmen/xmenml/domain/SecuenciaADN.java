package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.exceptions.SecuenciaADNNoAnalizadaException;

public class SecuenciaADN {

    private MatrizCadenaADN matrizADN;
    private Boolean esMutante;
    private Boolean secuenciaAnalizada = false;

    public SecuenciaADN(String[] secuenciasAdn) {
        this.matrizADN = new MatrizCadenaADN(secuenciasAdn);
    }


    /**
     * Indica si la Secuencia de ADN posee el gen mutante, debe comprobarse previamente, caso contrario
     * arrojara SecuenciaADNNoAnalizadaException.
     * @return
     */
    public Boolean esMutante() {

        if (!secuenciaAnalizada)
            throw new SecuenciaADNNoAnalizadaException();

        return this.esMutante;
    }

    public Boolean secuenciaAnalizada() {
        return this.secuenciaAnalizada;
    }

    /**
     * Serializa la informacion que poseen todas las cadenas de ADN, en un unico String.
     * @return
     */
    public String serializarADN() {
        return this.matrizADN.serializarCadena();
    }


    /**
     * Indica si la Secuencia de ADN posee un el Gen Mutante a partir de un comprobador de cadenas de ADN.
     * @param comprobadorADN
     * @return
     */
    public Boolean contieneAdnMutante(ComprobadorADN comprobadorADN) {

        Integer secuenciasCoincidentes = 0;

        int tamanio = this.matrizADN.numeroDeCadenas();
        this.esMutante = false;

        Integer coincidencias = 0;

        for (int i = 0; !esMutante && i < tamanio ; i++) {

            secuenciasCoincidentes += comprobadorADN.contarSecuenciasMutante(this.matrizADN.obtenerCadenaFila(i));
            esMutante = comprobadorADN.poseeGenMutante(secuenciasCoincidentes);

        }

        for (int j = 0; !esMutante && j < tamanio ; j++) {
            secuenciasCoincidentes += comprobadorADN.contarSecuenciasMutante(this.matrizADN.obtenerCadenaColumna(j));
            esMutante = comprobadorADN.poseeGenMutante(secuenciasCoincidentes);
        }

        int cantDiagonales = this.matrizADN.numeroDeDiagonales();

        for (int k = 0; !esMutante && k < cantDiagonales ; k++) {

            secuenciasCoincidentes += comprobadorADN.contarSecuenciasMutante(this.matrizADN.obtenerCadenaDiagonal(k));
            secuenciasCoincidentes += comprobadorADN.contarSecuenciasMutante(this.matrizADN.obtenerCadenaDiagonalInvertida(k));

            esMutante = comprobadorADN.poseeGenMutante(secuenciasCoincidentes);

        }

        this.secuenciaAnalizada = true;

        return this.esMutante;
    }
}
