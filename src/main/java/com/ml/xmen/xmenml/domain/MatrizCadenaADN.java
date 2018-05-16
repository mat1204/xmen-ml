package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.exceptions.ErrorIndiceCadenaException;

import java.util.Arrays;

/**
 * Clase utiliza para abstraer y utilizar de las secuencias de ADN como una matriz
 */
public class MatrizCadenaADN {

    private String[] adn;

    MatrizCadenaADN(String[] adns) {
        this.adn = Arrays.copyOf(adns, adns.length);
    }

    /**
     * Devuelve la fila indica de la matriz de Cadenas de ADN
     * @param numeroFila
     * @return
     */
    public String obtenerCadenaFila(Integer numeroFila) {
        if (numeroFila < this.adn.length)
            return this.adn[numeroFila];

        throw new ErrorIndiceCadenaException("Numero de fila fuera de banda");
    }

    /**
     * Retorna la columna indicada de la matriz de Cadenas de ADN
     * @param numeroColumna
     * @return
     */
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

    /**
     * Retorna una diagonal de la matriz de la cadena de ADNS.
     * Si la matriz es de tamanio NxN, la primer diagonal se ubica en (0, N-1) a (0, N-1)  {extremo inferio izquierdo}
     * La diagonal principal (N-1) en (0,0) a (N-1, N-1)
     * La ultima diagonal(2N-1) en (0,N-1) a (0, N-1)
     * @param numeroDiagonal
     * @return
     */
    public String obtenerCadenaDiagonal(Integer numeroDiagonal) {

        if ( numeroDiagonal < 0 || numeroDiagonal >= this.numeroDeDiagonales())
            throw new ErrorIndiceCadenaException();

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

    /**
     * Retorna la diagonal inversa de la matriz de la cadena de ADNS.
     * Si la matriz es de tamanio NxN, la primer(0) diagonal se ubica en (0, 0) a (0, 0)  {extremo inferio izquierdo}
     * La diagonal principal (N-1) en (N-1,0) a (0, N-1)
     * La ultima diagonal (2N-1) en (N-1,N-1) a (N-1, N-1)
     * @param numeroDiagonal
     * @return
     */
    public String obtenerCadenaDiagonalInvertida(Integer numeroDiagonal) {

        int tamanio = this.adn.length;
        StringBuffer sb = new StringBuffer();

        int i = tamanio - 1, j = 0;

        if (numeroDiagonal < tamanio-1) {
            i = numeroDiagonal;
            j = 0;
        }
        else if (numeroDiagonal > tamanio-1) {
            j = numeroDiagonal - (tamanio-1);
        }


        for ( ; 0 <= i && i < tamanio && 0 <= j &&  j < tamanio; i--, j++) {
            sb.append(this.adn[i].charAt(j));
        }

        return sb.toString();
    }

    /**
     * Retorna todas las cadenas de ADN, serializadas en un String, en FORMATO JSON
     * @return
     */
    public String serializarCadena() {
        StringBuffer sb = new StringBuffer();

        sb.append("[");

        for (String cadena : this.adn) {
            sb.append("'");
            sb.append(cadena);
            sb.append("',");
        }

        // le saco la ultima coma ','
        sb.setLength(sb.length()-1);

        sb.append("]");

        return sb.toString();
    }

    /*
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
    */

    /**
     * Indica el numero de cadenas que posee la matriz, siendo tambien el tamaño de la Matriz.
     * @return
     */
    public Integer numeroDeCadenas() {
        return this.adn.length;
    }

    /**
     * Indica el numero de diagonales que posee la matriz
     * @return
     */
    public Integer numeroDeDiagonales() {
        return this.adn.length * 2 - 1;
    }
}
