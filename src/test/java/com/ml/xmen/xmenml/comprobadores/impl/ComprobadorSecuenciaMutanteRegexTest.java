package com.ml.xmen.xmenml.comprobadores.impl;

import com.ml.xmen.xmenml.config.ParametrosADN;
import org.junit.Assert;
import org.junit.Test;

public class ComprobadorSecuenciaMutanteRegexTest {

    @Test
    public void secuenciaSimpleTest() {
        ParametrosADN parametrosADN = new ParametrosADN();

        parametrosADN.setSecuenciaPermitida("ATB");
        parametrosADN.setCoincidenciasMinimasEnSecuencia(3);
        parametrosADN.setSecuenciasParaGenMutante(2);

        ComprobadorSecuenciaMutanteRegex comp = new ComprobadorSecuenciaMutanteRegex(parametrosADN);

        Assert.assertTrue(comp.contieneSecuenciaMutante("AATTTBBA"));
        Assert.assertTrue(comp.contieneSecuenciaMutante("ATBATBATBATBAAATBTBTB"));
        Assert.assertTrue(comp.contieneSecuenciaMutante("AATBTBBBA"));
        Assert.assertTrue(comp.contieneSecuenciaMutante("AATTTBBABBCCA"));
        Assert.assertTrue(comp.contieneSecuenciaMutante("AATTTAAAAAAABBBBAABA"));

    }

    @Test
    public void secuenciasIncorrectasTest() {
        ParametrosADN parametrosADN = new ParametrosADN();

        parametrosADN.setSecuenciaPermitida("ATB");
        parametrosADN.setCoincidenciasMinimasEnSecuencia(4);
        parametrosADN.setSecuenciasParaGenMutante(2);

        ComprobadorSecuenciaMutanteRegex comp = new ComprobadorSecuenciaMutanteRegex(parametrosADN);

        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTCCA"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("ATBATBATBATBATBATBATBATB"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGGGGCCA"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGGGGCCAATADDDCCGGG"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGBBBGGBBGGBBGCCTAAADDDCCGGG"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTAABBAATTAGGGGGCCA"));
    }

}
