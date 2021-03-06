package com.ml.xmen.xmenml.comprobadores.impl;

import com.ml.xmen.xmenml.config.ParametrosADN;
import org.junit.Assert;
import org.junit.Test;

public class ComprobadorSecuenciaMutanteRegexTest {

    private ParametrosADN obtenerParametrosMock() {
        ParametrosADN parametrosADN = new ParametrosADN();

        parametrosADN.setSecuenciaPermitida("ATB");
        parametrosADN.setCoincidenciasMinimasEnSecuencia(4);
        parametrosADN.setSecuenciasParaGenMutante(2);

        return parametrosADN;
    }

    @Test
    public void verificarSecuenciaMutanteTest() {
        ParametrosADN parametrosADN = new ParametrosADN();

        parametrosADN.setSecuenciaPermitida("ATB");
        parametrosADN.setCoincidenciasMinimasEnSecuencia(3);
        parametrosADN.setSecuenciasParaGenMutante(3);

        ComprobadorSecuenciaMutanteRegex comp = new ComprobadorSecuenciaMutanteRegex(parametrosADN);

        Assert.assertTrue(comp.poseeGenMutante(4));
        Assert.assertTrue(comp.poseeGenMutante(3));
        Assert.assertFalse(comp.poseeGenMutante(2));
        Assert.assertFalse(comp.poseeGenMutante(1));
    }

    @Test
    public void candenaAdnComprobableTest() {
        ParametrosADN parametrosADN = new ParametrosADN();

        parametrosADN.setSecuenciaPermitida("ATB");
        parametrosADN.setCoincidenciasMinimasEnSecuencia(5);
        parametrosADN.setSecuenciasParaGenMutante(3);

        ComprobadorSecuenciaMutanteRegex comp = new ComprobadorSecuenciaMutanteRegex(parametrosADN);

        Assert.assertTrue(comp.esADNComprobable("AAAAA"));
        Assert.assertTrue(comp.esADNComprobable("AAAAAAAA"));

        Assert.assertFalse(comp.esADNComprobable("AAA"));
        Assert.assertFalse(comp.esADNComprobable("A"));
        Assert.assertFalse(comp.esADNComprobable(""));
    }

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
        ParametrosADN parametrosADN = obtenerParametrosMock();

        ComprobadorSecuenciaMutanteRegex comp = new ComprobadorSecuenciaMutanteRegex(parametrosADN);

        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTCCA"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("ATBATBATBATBATBATBATBATB"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGGGGCCA"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGGGGCCAATADDDCCGGG"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTGGBBBGGBBGGBBGCCTAAADDDCCGGG"));
        Assert.assertFalse(comp.contieneSecuenciaMutante("AATTTAABBAATTAGGGGGCCA"));
    }

    @Test
    public void secuenciasConMultiplesCoincidenciasTest() {

        ComprobadorSecuenciaMutanteRegex comp = new ComprobadorSecuenciaMutanteRegex(obtenerParametrosMock());

        Assert.assertEquals(Integer.valueOf(3), comp.contarSecuenciasMutante("AAAATTTTCCAAAAA"));
        Assert.assertEquals(Integer.valueOf(4), comp.contarSecuenciasMutante("AAAATAAAATAAAATBBBBBBBBBBBBBBBBBBBBBBBBB"));
        Assert.assertEquals(Integer.valueOf(1), comp.contarSecuenciasMutante("AAAAAAAAAAAAAAA"));
        Assert.assertEquals(Integer.valueOf(2), comp.contarSecuenciasMutante("AAAATTTTBBAA"));
        Assert.assertEquals(Integer.valueOf(0), comp.contarSecuenciasMutante("AATTBBAATTTAAATAAATAAA"));
        Assert.assertEquals(Integer.valueOf(1), comp.contarSecuenciasMutante("AATTBBAAAATTTAAATAAATAAA"));

    }

}
