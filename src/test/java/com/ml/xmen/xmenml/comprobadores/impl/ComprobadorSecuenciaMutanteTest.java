package com.ml.xmen.xmenml.comprobadores.impl;

import com.ml.xmen.xmenml.comprobadores.impl.ComprobadorSecuenciaMutante;
import com.ml.xmen.xmenml.config.ParametrosADN;
import org.junit.Assert;
import org.junit.Test;

public class ComprobadorSecuenciaMutanteTest {

    @Test
    public void verificarSecuenciaMutanteTest() {
        ParametrosADN parametrosADN = new ParametrosADN();

        parametrosADN.setSecuenciaPermitida("ATB");
        parametrosADN.setCoincidenciasMinimasEnSecuencia(3);
        parametrosADN.setSecuenciasParaGenMutante(3);

        ComprobadorSecuenciaMutante comp = new ComprobadorSecuenciaMutante(parametrosADN);

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

        ComprobadorSecuenciaMutante comp = new ComprobadorSecuenciaMutante(parametrosADN);

        Assert.assertTrue(comp.esADNComprobable("AAAAA"));
        Assert.assertTrue(comp.esADNComprobable("AAAAAAAA"));

        Assert.assertFalse(comp.esADNComprobable("AAA"));
        Assert.assertFalse(comp.esADNComprobable("A"));
        Assert.assertFalse(comp.esADNComprobable(""));
    }


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

    @Test
    public void secuenciasConMultiplesCoincidenciasTest() {

        ParametrosADN parametrosADN = new ParametrosADN();

        parametrosADN.setSecuenciaPermitida("ATB");
        parametrosADN.setCoincidenciasMinimasEnSecuencia(4);
        parametrosADN.setSecuenciasParaGenMutante(3);

        ComprobadorSecuenciaMutante comp = new ComprobadorSecuenciaMutante(parametrosADN);

        Assert.assertEquals(Integer.valueOf(3), comp.contarSecuenciasMutante("AAAATTTTCCAAAAA"));
        Assert.assertEquals(Integer.valueOf(4), comp.contarSecuenciasMutante("AAAATAAAATAAAATBBBBBBBBBBBBBBBBBBBBBBBBB"));
        Assert.assertEquals(Integer.valueOf(1), comp.contarSecuenciasMutante("AAAAAAAAAAAAAAA"));
        Assert.assertEquals(Integer.valueOf(2), comp.contarSecuenciasMutante("AAAATTTTBBAA"));
        Assert.assertEquals(Integer.valueOf(0), comp.contarSecuenciasMutante("AATTBBAATTTAAATAAATAAA"));
        Assert.assertEquals(Integer.valueOf(1), comp.contarSecuenciasMutante("AATTBBAAAATTTAAATAAATAAA"));

    }

}
