package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.exceptions.ErrorIndiceCadenaException;

import java.util.Arrays;

public class SecuenciaADN {

    String adn[];

    Boolean esMutante;

    SecuenciaADN(String[] secuenciasAdn) {
        this.adn = Arrays.copyOf(secuenciasAdn, secuenciasAdn.length);
    }

    public Boolean getEsMutante() {
        return this.esMutante;
    }

    public String obtenerCadenaFila(Integer numeroFila) {
        if (numeroFila < this.adn.length)
            return this.adn[numeroFila];

        throw new ErrorIndiceCadenaException("Numero de fila fuera de banda");
    }

    public String obtenerCadenaColumna(Integer numeroColumna) {

        StringBuffer sb = new StringBuffer(this.adn.length);

        try {
            for (String cadenaAdn : this.adn)
                sb.append(cadenaAdn.charAt(numeroColumna));
        }
        catch(Exception e) {
            throw new ErrorIndiceCadenaException("Numero de columna fuera de banda");
        }

        return sb.toString();
    }


    public String obtenerCadenaDiagonal(DiagonalSecuenciaADN diagonalSecuenciaADN) {

        int i = 0;
        int j = 0;
        int incremeto = 1;

        int lengthCadena = this.adn.length;
        StringBuffer sb = new StringBuffer(lengthCadena);

        if (diagonalSecuenciaADN.equals(DiagonalSecuenciaADN.SECUNDARIA)) {
            j = lengthCadena - 1;
            incremeto = -1;
        }


        for ( ; i < lengthCadena && 0 <= j &&  j < lengthCadena; i++, j += incremeto) {
            sb.append(this.adn[i].charAt(j));
        }

        return sb.toString();
    }


    public Integer numeroDeCadenas() {
        return this.adn.length;
    }
}
