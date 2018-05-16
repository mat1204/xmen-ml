package com.ml.xmen.xmenml.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class EstadisticasGlobalDTO {

    private Long cantidadHumanos;
    private Long cantidadMutantes;
    private BigDecimal ratioMutante;

    public EstadisticasGlobalDTO(Long cantidadHumanos, Long cantidadMutantes, BigDecimal ratioMutante) {
        this.cantidadHumanos = cantidadHumanos;
        this.cantidadMutantes = cantidadMutantes;
        this.ratioMutante = ratioMutante;
    }

    @JsonProperty("count_human_dna")
    public Long getCantidadHumanos() {
        return cantidadHumanos;
    }

    @JsonProperty("count_mutant_dna")
    public Long getCantidadMutantes() {
        return cantidadMutantes;
    }

    @JsonProperty("ratio")
    public BigDecimal getRatioMutante() {
        return ratioMutante;
    }
}
