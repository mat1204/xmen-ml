package com.ml.xmen.xmenml.comprobadores.impl;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.config.ParametrosADN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

//@Component
public class ComprobadorSecuenciaMutanteRegex implements ComprobadorADN {

    final private Integer coincidenciasRequeridasEnSecuencia;
    final private Integer secuenciasRequeridasParaGenMutante;
    final private Pattern patternMutante;

    @Autowired
    public ComprobadorSecuenciaMutanteRegex(ParametrosADN parametrosADN) {
        this.coincidenciasRequeridasEnSecuencia = parametrosADN.getCoincidenciasMinimasEnSecuencia();
        this.secuenciasRequeridasParaGenMutante = parametrosADN.getSecuenciasParaGenMutante();

        this.patternMutante = Pattern.compile(this.construirPattern(parametrosADN.getSecuenciaPermitida()));
    }

    private String construirPattern(String secuenciaPermitida) {

        StringBuffer sb = new StringBuffer();

        sb.append("^.*");
        sb.append("(");

        for (Character caracter : secuenciaPermitida.toUpperCase().toCharArray() ) {
            sb.append( caracter );


            sb.append("{");
            sb.append(this.coincidenciasRequeridasEnSecuencia);
            sb.append(",}|");
        }

        // saco el ultimo caracter de "|"
        sb.setLength(sb.length() - 1);

        sb.append(")");
        sb.append(".*$");

        return sb.toString();
    }


    ComprobadorSecuenciaMutanteRegex(Integer coincidenciasMinimas, Integer secuenciasNecesarias ) {
        this.coincidenciasRequeridasEnSecuencia = coincidenciasMinimas;
        this.secuenciasRequeridasParaGenMutante = secuenciasNecesarias;
        this.patternMutante = Pattern.compile(".*");
    }

    @Override
    public Boolean contieneSecuenciaMutante(String cadenaAdn) {
        return this.patternMutante.matcher(cadenaAdn).matches();
    }

    @Override
    public Boolean esADNComprobable(String cadenaADN){
        return cadenaADN.length() >= this.coincidenciasRequeridasEnSecuencia.intValue();
    }

    @Override
    public Boolean poseeGenMutante(Integer numeroSecuenciasCoincidencias) {
        return numeroSecuenciasCoincidencias >= this.secuenciasRequeridasParaGenMutante;
    }

}
