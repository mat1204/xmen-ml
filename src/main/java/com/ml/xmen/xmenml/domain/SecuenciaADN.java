package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;

public class SecuenciaADN {

    private MatrizCadenaADN matrizADN;
    private Boolean esMutante;

    public SecuenciaADN(String[] secuenciasAdn) {
        this.matrizADN = new MatrizCadenaADN(secuenciasAdn);
    }


    public Boolean getEsMutante() {
        return this.esMutante;
    }


    public Boolean contieneAdnMutante(ComprobadorADN comprobadorADN) {

        Integer secuenciasCoincidentes = 0;

        int tamanio = this.matrizADN.numeroDeCadenas();
        this.esMutante = false;

        for (int i = 0; !esMutante && i < tamanio ; i++) {
            if (comprobadorADN.contieneSecuenciaMutante(this.matrizADN.obtenerCadenaFila(i)))
                esMutante = comprobadorADN.poseeGenMutante(++secuenciasCoincidentes);
        }

        for (int j = 0; !esMutante && j < tamanio ; j++) {
            if (comprobadorADN.contieneSecuenciaMutante(this.matrizADN.obtenerCadenaColumna(j)))
                esMutante = comprobadorADN.poseeGenMutante(++secuenciasCoincidentes);
        }

        int cantDiagonales = this.matrizADN.numeroDeDiagonales();

        for (int k = 0; !esMutante && k < cantDiagonales ; k++) {
            String cadenaDiagonal = this.matrizADN.obtenerCadenaDiagonal(k);

            if (comprobadorADN.contieneSecuenciaMutante(cadenaDiagonal))
                esMutante = comprobadorADN.poseeGenMutante(++secuenciasCoincidentes);

            cadenaDiagonal = this.matrizADN.obtenerCadenaDiagonalInvertida(k);
            if (comprobadorADN.contieneSecuenciaMutante(cadenaDiagonal))
                esMutante = comprobadorADN.poseeGenMutante(++secuenciasCoincidentes);
        }

        return this.esMutante;
    }
}
