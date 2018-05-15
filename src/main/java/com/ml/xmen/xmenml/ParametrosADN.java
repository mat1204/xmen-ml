package com.ml.xmen.xmenml;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("adn.mutante")
public class ParametrosADN {

    public ParametrosADN() {}

    private Integer coincidenciasMinimasEnSecuencia = 4;
    private Integer secuenciasParaGenMutante = 2;


    public Integer getCoincidenciasMinimasEnSecuencia() {
        return coincidenciasMinimasEnSecuencia;
    }

    public void setCoincidenciasMinimasEnSecuencia(Integer coincidenciasMinimasEnSecuencia) {
        this.coincidenciasMinimasEnSecuencia = coincidenciasMinimasEnSecuencia;
    }

    public Integer getSecuenciasParaGenMutante() {
        return secuenciasParaGenMutante;
    }

    public void setSecuenciasParaGenMutante(Integer secuenciasParaGenMutante) {
        this.secuenciasParaGenMutante = secuenciasParaGenMutante;
    }

}
