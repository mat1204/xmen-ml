package com.ml.xmen.xmenml.controllers.requestbody;

import javax.validation.constraints.NotNull;

public class ComprobacionMutanteRequest {

    @NotNull
    public String[] dna;

}
