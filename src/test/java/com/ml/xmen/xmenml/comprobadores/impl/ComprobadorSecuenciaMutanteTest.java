package com.ml.xmen.xmenml.comprobadores.impl;

import com.ml.xmen.xmenml.comprobadores.impl.ComprobadorSecuenciaMutante;
import org.junit.Assert;
import org.junit.Test;

public class ComprobadorSecuenciaMutanteTest {

    @Test
    public void secuenciaSimpleTest() {
        ComprobadorSecuenciaMutante comp = new ComprobadorSecuenciaMutante(4, 2);

        Assert.assertTrue(comp.contieneSecuenciaMutante("AATTTCCA", 2));
        Assert.assertTrue(comp.contieneSecuenciaMutante("AATTTCCA", 3));
        Assert.assertTrue(comp.contieneSecuenciaMutante("AATTTGGGGGCCA", 5));
        Assert.assertTrue(comp.contieneSecuenciaMutante("AATTTAAAAAAAGGGGGCCA", 6));

    }

    @Test
    public void secuenciasIncorrectasTest() {
        ComprobadorSecuenciaMutante comp = new ComprobadorSecuenciaMutante(4, 2);

        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTCCA", 4));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGGGGCCA", 7));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGGGGCCAAAADDDCCGGG", 7));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGGGGCCAAAADDDCCGGG", 7));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTAAAAAGGGGGCCA", 6));
    }

}
