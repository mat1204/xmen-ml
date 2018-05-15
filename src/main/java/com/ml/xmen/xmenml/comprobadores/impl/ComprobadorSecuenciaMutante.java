package com.ml.xmen.xmenml.comprobadores.impl;

import com.ml.xmen.xmenml.ParametrosADN;
import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
    public Boolean contieneSecuenciaMutante(String candenaAdn) {
        return this.esADNComprobable(candenaAdn) && this.contieneSecuenciaMutante(candenaAdn, this.coincidenciasRequeridasEnSecuencia);
    }

    @Override
    public Boolean esADNComprobable(String cadenaADN){
        return cadenaADN.length() >= this.coincidenciasRequeridasEnSecuencia.intValue();
    }

    @Override
    public Boolean poseeGenMutante(Integer numeroSecuenciasCoincidencias) {
        return numeroSecuenciasCoincidencias >= this.secuenciasRequeridasParaGenMutante;
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
