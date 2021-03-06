package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.exceptions.SecuenciaADNNoAnalizadaException;
import org.junit.Assert;
import org.junit.Test;

public class SecuenciaADNTest {


    ComprobadorADN mockComprobadorAdn() {
        return new ComprobadorADN() {
            @Override
            public Boolean contieneSecuenciaMutante(String cadenaADN) {
                return cadenaADN.contains("AAAAAA");
            }

            @Override
            public Integer contarSecuenciasMutante(String cadenaADN) {
                return cadenaADN.contains("AAA") ? 1 : 0;
            }

            @Override
            public Boolean poseeGenMutante(Integer numeroCoincidencias) {
                return numeroCoincidencias >= 3;
            }

            @Override
            public Boolean esADNComprobable(String cadenaADN) {
                return cadenaADN.length() > 3;
            }
        };
    }
    @Test
    public void secuenciaAnalizadaTest() {
        String[] adn = {"AAAAAA",
                "AABBCC",
                "AAAAAA",
                "AAABBB",
                "AAAAAA",
                "AABBCC"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertTrue(secuenciaADN.secuenciaAnalizada());
    }


    @Test
    public void secuenciaMutanteSimpleTest() {
        String[] adn = {"AAAAAA",
                        "AABBCC",
                        "AAAAAA",
                        "AAABBB",
                        "AAAAAA",
                        "AABBCC"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertTrue(secuenciaADN.esMutante());
    }


    @Test
    public void secuenciaNoMutanteSimpleTest() {
        String[] adn = {"AABAAB",
                        "AABBCC",
                        "CCBBCC",
                        "ABCBBC",
                        "BABBBC",
                        "AABBCC"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertFalse(poseeAdnMutante);
    }

    @Test
    public void secuenciaMutanteConDiagonalTest() {
        String[] adn = {"AAAAAA",
                        "AABBCC",
                        "ABABCC",
                        "ABBACC",
                        "ABCBAC",
                        "ABBBBA"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertTrue(poseeAdnMutante);
    }



    @Test
    public void secuenciaMutanteMultiplesEnMismaDiagonalTest() {
        String[] adn = {
                "ACCCCCCCCCCC",
                "CACCCACCCCCC",
                "CCACCCACCCCC",
                "CCCACCCACCCC",
                "CCCCACCCACCC",
                "CCCCCACCCACC",
                "CCACCCACCCAC",
                "CCCACCCACCCC",
                "CCCCACCCCCCC",
                "CCCCCACCCCCC",
                "CCCCCCACCCCC",
                "CCCCCCCACCCC"
        };

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertTrue(secuenciaADN.secuenciaAnalizada());
        Assert.assertTrue(poseeAdnMutante);
    }

    @Test
    public void secuenciaMutanteConMultiplesDiagonalesInvertidasTest() {
        String[] adn = {"CCCCCCCCCCCC",
                        "CCCCCACCCCCC",
                        "CCCCACCCCCCC",
                        "CCCACCCCCCCC",
                        "CCACCCACCCCC",
                        "CACCCACCCCCC",
                        "ACCCACCCACCC",
                        "CCCACCCACCCC",
                        "CCACCCACCCCC",
                        "CACCCACCCCCC",
                        "ACCCACCCCCCC",
                        "CCCACCCCCCCC"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertTrue(secuenciaADN.secuenciaAnalizada());
        Assert.assertTrue(poseeAdnMutante);
    }
    @Test
    public void secuenciaMutanteConMultiplesDiagonalesCruzadasTest() {
        String[] adn = {"CCCCCCCCCCCC",
                        "CCACCCCCCCCC",
                        "CCCACCCCCCCC",
                        "CCCCAACCCCCC",
                        "ACCCAACCCCCC",
                        "CACACCACCCCC",
                        "CCACCCCACCCC",
                        "CACACCCCCCCC",
                        "ACCCACCCCCCC",
                        "CCCCCACCCCCC",
                        "CCCCCCCCCCCC",
                        "CCCCCCCCCCCC"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertTrue(secuenciaADN.secuenciaAnalizada());
        Assert.assertTrue(poseeAdnMutante);
    }


    @Test
    public void secuenciaCortaNoMutanteSimpleTest() {
        String[] adn = {"ABA",
                        "BAB",
                        "ABA"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertTrue(secuenciaADN.secuenciaAnalizada());
        Assert.assertFalse(poseeAdnMutante);
    }


    @Test(expected = SecuenciaADNNoAnalizadaException.class)
    public void secuenciaNoAnalizadaTest() {
        String[] adn = {"AAA",
                "AAA",
                "AAA"};

        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Assert.assertFalse(secuenciaADN.secuenciaAnalizada());

        Boolean esMutante = secuenciaADN.esMutante();
    }

    @Test
    public void serializacionTest() {
        String[] adn = {"AAA",
                "BBB",
                "CCC"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertEquals("['AAA','BBB','CCC']", secuenciaADN.serializarADN());
    }
}
