package com.ml.xmen.xmenml.comprobadores.impl;

import com.ml.xmen.xmenml.config.ParametrosADN;
import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class ComprobadorSecuenciaMutante implements ComprobadorADN {

    final private Integer coincidenciasRequeridasEnSecuencia;
    final private Integer secuenciasRequeridasParaGenMutante;

    @Autowired
    public ComprobadorSecuenciaMutante(ParametrosADN parametrosADN) {
        this.coincidenciasRequeridasEnSecuencia = parametrosADN.getCoincidenciasMinimasEnSecuencia();
        this.secuenciasRequeridasParaGenMutante = parametrosADN.getSecuenciasParaGenMutante();
    }


    ComprobadorSecuenciaMutante(Integer coincidenciasMinimas, Integer secuenciasNecesarias ) {
        this.coincidenciasRequeridasEnSecuencia = coincidenciasMinimas;
        this.secuenciasRequeridasParaGenMutante = secuenciasNecesarias;
    }

    @Override
    public Boolean contieneSecuenciaMutante(String cadenaAdn) {
        return this.esADNComprobable(cadenaAdn) && this.contarSecuenciasMutante(cadenaAdn) > 0;
    }

    public Boolean contieneSecuenciaMutante(String cadenaAdn, Integer coincidenciasMinimas ) {
        return this.esADNComprobable(cadenaAdn) && this.contarSecuenciasMutante(cadenaAdn, coincidenciasMinimas) > 0;
    }

    @Override
    public Integer contarSecuenciasMutante(String cadenaAdn) {
        return this.contarSecuenciasMutante(cadenaAdn, this.coincidenciasRequeridasEnSecuencia);
    }

    @Override
    public Boolean esADNComprobable(String cadenaADN){
        return cadenaADN.length() >= this.coincidenciasRequeridasEnSecuencia.intValue();
    }

    @Override
    public Boolean poseeGenMutante(Integer numeroSecuenciasCoincidencias) {
        return numeroSecuenciasCoincidencias >= this.secuenciasRequeridasParaGenMutante;
    }

    public Integer contarSecuenciasMutante(String cadenaAdn, Integer minimos) {
        Boolean esSecuenciaMutante = false;

        Integer coincidencias = 0;

        Integer coincidenciaLocal = 0;
        Character caracterCoincidencia = cadenaAdn.charAt(0);

        Boolean coincidenciaLocalEncontrada = false;

        for (int i = 0 ; !esSecuenciaMutante && i < cadenaAdn.length(); i++) {
            Character caracter = cadenaAdn.charAt(i);


            if (caracter.equals(caracterCoincidencia)) {
                coincidenciaLocal++;
            }
            else {
                coincidenciaLocal = 1;
                caracterCoincidencia = caracter;
                coincidenciaLocalEncontrada = false;
            }


            if (!coincidenciaLocalEncontrada && coincidenciaLocal >= minimos) {
                coincidencias++;
                coincidenciaLocalEncontrada = true;
            }
        }

        return coincidencias;
    }

}
