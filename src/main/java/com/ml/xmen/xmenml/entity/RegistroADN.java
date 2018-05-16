package com.ml.xmen.xmenml.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "registro_adn")
public class RegistroADN {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 2048)
    private String secuenciasADN;

    @NotNull
    private Boolean esMutante;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecuenciasADN() {
        return secuenciasADN;
    }

    public void setSecuenciasADN(String secuenciasADN) {
        this.secuenciasADN = secuenciasADN;
    }

    public Boolean getEsMutante() {
        return esMutante;
    }

    public void setEsMutante(Boolean esMutante) {
        this.esMutante = esMutante;
    }
}
