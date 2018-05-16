package com.ml.xmen.xmenml.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estadistica")
public class Estadistica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated
    private TipoEstadistica tipoEstadistica;

    private Long valor;

    public Estadistica() {}

    public Estadistica(TipoEstadistica tipoEstadistica) {
        this.tipoEstadistica = tipoEstadistica;
        this.valor = 0l;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoEstadistica getTipoEstadistica() {
        return tipoEstadistica;
    }

    public void setTipoEstadistica(TipoEstadistica tipoEstadistica) {
        this.tipoEstadistica = tipoEstadistica;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public void contar() {
        if (this.valor == null)
            this.valor = 1l;
        this.valor++;
    }
}
