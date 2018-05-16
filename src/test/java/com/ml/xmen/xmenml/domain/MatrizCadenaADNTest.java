package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.exceptions.ErrorIndiceCadenaException;
import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class MatrizCadenaADNTest {

    @Test
    public void obtenerFilaTest() {
        MatrizCadenaADN matrizADN = new MatrizCadenaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        Assert.assertTrue(matrizADN.numeroDeCadenas() == 4);

        Assert.assertTrue(matrizADN.obtenerCadenaFila(0).equals("AAAB"));
        Assert.assertTrue(matrizADN.obtenerCadenaFila(1).equals("BBAW"));
        Assert.assertTrue(matrizADN.obtenerCadenaFila(2).equals("SSDW"));
        Assert.assertTrue(matrizADN.obtenerCadenaFila(3).equals("AWEA"));

    }

    @Test
    public void obtenerColumnaTest() {
        MatrizCadenaADN matrizADN = new MatrizCadenaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        Assert.assertTrue(matrizADN.numeroDeCadenas() == 4);

        Assert.assertTrue(matrizADN.obtenerCadenaColumna(0).equals("ABSA"));
        Assert.assertTrue(matrizADN.obtenerCadenaColumna(1).equals("ABSW"));
        Assert.assertTrue(matrizADN.obtenerCadenaColumna(2).equals("AADE"));
        Assert.assertTrue(matrizADN.obtenerCadenaColumna(3).equals("BWWA"));
    }



    @Test
    public void obtenerDiagonalNumericaTest() {
        MatrizCadenaADN matrizADN = new MatrizCadenaADN(Arrays.array(
                                                        "AAABG",
                                                                "BBAWT",
                                                                "SSDWG",
                                                                "GGTTT",
                                                                "AWEAG"));

        Assert.assertEquals("A", matrizADN.obtenerCadenaDiagonal(0));
        Assert.assertEquals("GW", matrizADN.obtenerCadenaDiagonal(1));
        Assert.assertEquals("SGE", matrizADN.obtenerCadenaDiagonal(2));
        Assert.assertEquals("BSTA", matrizADN.obtenerCadenaDiagonal(3));
        Assert.assertEquals("ABDTG", matrizADN.obtenerCadenaDiagonal(4));
        Assert.assertEquals("AAWT", matrizADN.obtenerCadenaDiagonal(5));
        Assert.assertEquals("AWG", matrizADN.obtenerCadenaDiagonal(6));
        Assert.assertEquals("BT", matrizADN.obtenerCadenaDiagonal(7));
        Assert.assertEquals("G", matrizADN.obtenerCadenaDiagonal(8));

    }

    @Test
    public void obtenerDiagonalInvertidaNumericaTest() {
        MatrizCadenaADN matrizADN = new MatrizCadenaADN(Arrays.array(
                                                        "AAABG",
                                                                "BBAWT",
                                                                "SSDWG",
                                                                "GGTTT",
                                                                "AWEAG"));

        Assert.assertEquals("A", matrizADN.obtenerCadenaDiagonalInvertida(0));
        Assert.assertEquals("BA", matrizADN.obtenerCadenaDiagonalInvertida(1));
        Assert.assertEquals("SBA", matrizADN.obtenerCadenaDiagonalInvertida(2));
        Assert.assertEquals("GSAB", matrizADN.obtenerCadenaDiagonalInvertida(3));
        Assert.assertEquals("AGDWG", matrizADN.obtenerCadenaDiagonalInvertida(4));
        Assert.assertEquals("WTWT", matrizADN.obtenerCadenaDiagonalInvertida(5));
        Assert.assertEquals("ETG", matrizADN.obtenerCadenaDiagonalInvertida(6));
        Assert.assertEquals("AT", matrizADN.obtenerCadenaDiagonalInvertida(7));
        Assert.assertEquals("G", matrizADN.obtenerCadenaDiagonalInvertida(8));

    }




    @Test(expected = ErrorIndiceCadenaException.class)
    public void numeroFilafueraDeRangoTest() {
        MatrizCadenaADN matrizADN = new MatrizCadenaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        matrizADN.obtenerCadenaColumna(5);
    }


    @Test(expected = ErrorIndiceCadenaException.class)
    public void numeroColumnafueraDeRangoTest() {
        MatrizCadenaADN matrizADN = new MatrizCadenaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        matrizADN.obtenerCadenaColumna(8);
    }

}
