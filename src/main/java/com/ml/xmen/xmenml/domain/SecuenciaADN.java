package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.exceptions.ErrorIndiceCadenaException;

import java.util.Arrays;

public class SecuenciaADN {

    private String adn[];
    private Boolean esMutante;
    private Integer secuenciasParaGenMutante;

    public SecuenciaADN(String[] secuenciasAdn) {
        this(secuenciasAdn, 4);
    }

    public SecuenciaADN(String[] secuenciasAdn, Integer secuenciasParaGenMutante) {
        this.adn = Arrays.copyOf(secuenciasAdn, secuenciasAdn.length);
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

    public String obtenerCadenaDiagonal(Integer numeroDiagonal) {
        int i = 0, j = 0;

        int tamanio = this.adn.length;
        StringBuffer sb = new StringBuffer();


        if (numeroDiagonal < tamanio-1) {
            i = tamanio - 1 - numeroDiagonal;
            j = 0;
        }
        else if (numeroDiagonal > tamanio-1) {
            i = 0;
            j = numeroDiagonal - ( tamanio - 1 );
        }


        for ( ; 0 <= i && i < tamanio && 0 <= j &&  j < tamanio; i++, j++) {
            sb.append(this.adn[i].charAt(j));
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


    public Boolean getEsMutante() {
        return this.esMutante;
    }

    public Boolean contieneAdnMutante(ComprobadorADN comprobadorADN) {

        Integer secuenciasCoincidentes = 0;

        int tamanio = this.numeroDeCadenas();

        for (int i = 0; !esMutante && i < tamanio ; i++) {
            if (comprobadorADN.contieneSecuenciaMutante(this.obtenerCadenaFila(i)))
                esMutante = comprobadorADN.poseeGenMutante(++secuenciasCoincidentes);
        }

        for (int j = 0; !esMutante && j < tamanio ; j++) {
            if (comprobadorADN.contieneSecuenciaMutante(this.obtenerCadenaColumna(j)))
                esMutante = comprobadorADN.poseeGenMutante(++secuenciasCoincidentes);
        }

        int cantDiagonales = 2 * tamanio - 2;

        for (int k = 0; !esMutante && k < cantDiagonales ; k++) {
            String cadenaDiagonal = this.obtenerCadenaDiagonal(k);
            if (comprobadorADN.contieneSecuenciaMutante(cadenaDiagonal))
                esMutante = comprobadorADN.poseeGenMutante(++secuenciasCoincidentes);
        }


        return this.esMutante;
    }
}
