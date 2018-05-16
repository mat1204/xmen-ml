package com.ml.xmen.xmenml.controllers.requestbody;

import javax.validation.constraints.NotNull;

public class ComprobacionMutanteRequest {

    private String[] dna;

    public String[] getDna() {
        return this.dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
