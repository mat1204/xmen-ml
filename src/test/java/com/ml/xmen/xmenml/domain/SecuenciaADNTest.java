package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.exceptions.ErrorIndiceCadenaException;
import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class SecuenciaADNTest {

    @Test
    public void obtenerFilaTest() {
        SecuenciaADN secuenciaADN = new SecuenciaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        Assert.assertTrue(secuenciaADN.numeroDeCadenas() == 4);

        Assert.assertTrue(secuenciaADN.obtenerCadenaFila(0).equals("AAAB"));
        Assert.assertTrue(secuenciaADN.obtenerCadenaFila(1).equals("BBAW"));
        Assert.assertTrue(secuenciaADN.obtenerCadenaFila(2).equals("SSDW"));
        Assert.assertTrue(secuenciaADN.obtenerCadenaFila(3).equals("AWEA"));

    }

    @Test
    public void obtenerColumnaTest() {
        SecuenciaADN secuenciaADN = new SecuenciaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        Assert.assertTrue(secuenciaADN.numeroDeCadenas() == 4);

        Assert.assertTrue(secuenciaADN.obtenerCadenaColumna(0).equals("ABSA"));
        Assert.assertTrue(secuenciaADN.obtenerCadenaColumna(1).equals("ABSW"));
        Assert.assertTrue(secuenciaADN.obtenerCadenaColumna(2).equals("AADE"));
        Assert.assertTrue(secuenciaADN.obtenerCadenaColumna(3).equals("BWWA"));
    }


    @Test
    public void obtenerDiagonalTest() {
        SecuenciaADN secuenciaADN = new SecuenciaADN(Arrays.array(
                                                        "AAABG",
                                                                "BBAWT",
                                                                "SSDWG",
                                                                "GGTTT",
                                                                "AWEAG"));

        Assert.assertTrue(secuenciaADN.obtenerCadenaDiagonal(DiagonalSecuenciaADN.PRINCIPAL).equals("ABDTG"));
        Assert.assertTrue(secuenciaADN.obtenerCadenaDiagonal(DiagonalSecuenciaADN.SECUNDARIA).equals("GWDGA"));

    }



    @Test
    public void obtenerDiagonalNumericaTest() {
        SecuenciaADN secuenciaADN = new SecuenciaADN(Arrays.array(
                                                        "AAABG",
                                                                "BBAWT",
                                                                "SSDWG",
                                                                "GGTTT",
                                                                "AWEAG"));

        Assert.assertEquals("A", secuenciaADN.obtenerCadenaDiagonal(0));
        Assert.assertEquals("GW", secuenciaADN.obtenerCadenaDiagonal(1));
        Assert.assertEquals("SGE", secuenciaADN.obtenerCadenaDiagonal(2));
        Assert.assertEquals("BSTA", secuenciaADN.obtenerCadenaDiagonal(3));
        Assert.assertEquals("ABDTG", secuenciaADN.obtenerCadenaDiagonal(4));
        Assert.assertEquals("AAWT", secuenciaADN.obtenerCadenaDiagonal(5));
        Assert.assertEquals("AWG", secuenciaADN.obtenerCadenaDiagonal(6));
        Assert.assertEquals("BT", secuenciaADN.obtenerCadenaDiagonal(7));
        Assert.assertEquals("G", secuenciaADN.obtenerCadenaDiagonal(8));

    }




    @Test(expected = ErrorIndiceCadenaException.class)
    public void numeroFilafueraDeRangoTest() {
        SecuenciaADN secuenciaADN = new SecuenciaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        secuenciaADN.obtenerCadenaColumna(5);
    }


    @Test(expected = ErrorIndiceCadenaException.class)
    public void numeroColumnafueraDeRangoTest() {
        SecuenciaADN secuenciaADN = new SecuenciaADN(Arrays.array("AAAB", "BBAW", "SSDW", "AWEA"));

        secuenciaADN.obtenerCadenaColumna(8);
    }

}
