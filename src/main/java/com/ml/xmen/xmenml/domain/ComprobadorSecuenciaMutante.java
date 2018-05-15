package com.ml.xmen.xmenml.domain;

public class ComprobadorSecuenciaMutante {

    static final private Integer COINCIDENCIA_DE_CADENA_MUTANTE = 4;


    public ComprobadorSecuenciaMutante() {

    }

    public Boolean contieneSecuenciaMutante(String candenaAdn) {
        return this.contieneSecuenciaMutante(candenaAdn, COINCIDENCIA_DE_CADENA_MUTANTE);
    }

    public Boolean contieneSecuenciaMutante(String cadenaAdn, Integer coincidenciasRequeridas) {

        Boolean esSecuenciaMutante = false;

        Integer coincidenciaLocal = 0;
        Character caracterCoincidencia = cadenaAdn.charAt(0);

        for (int i = 0 ; !esSecuenciaMutante && i < cadenaAdn.length(); i++) {
            Character caracter = cadenaAdn.charAt(i);

            if (caracter.equals(caracterCoincidencia)) {
                coincidenciaLocal++;
            }
            else {
                coincidenciaLocal = 1;
                caracterCoincidencia = caracter;
            }


            if (coincidenciaLocal >= coincidenciasRequeridas)
                esSecuenciaMutante = true;
        }

        return esSecuenciaMutante;
    }
}
