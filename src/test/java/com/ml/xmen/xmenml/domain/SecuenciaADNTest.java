package com.ml.xmen.xmenml.domain;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
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

        Assert.assertTrue(secuenciaADN.getEsMutante());
    }


    @Test
    public void secuenciaNoMutanteSimpleTest() {
        String[] adn = {"AAAAAA",
                        "AABBCC",
                        "CCBBCC",
                        "AACBBC",
                        "AABBBC",
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
    public void secuenciaMutanteConMultiplesDiagonalesTest() {
        String[] adn = {"ACCCCCCCCCCC",
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
                        "CCCCCCCACCCC"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

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

        Assert.assertTrue(poseeAdnMutante);
    }


    @Test
    public void secuenciaCortaNoMutanteSimpleTest() {
        String[] adn = {"AAA",
                        "AAA",
                        "AAA"};

        ComprobadorADN comprobadorADN = this.mockComprobadorAdn();
        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean poseeAdnMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        Assert.assertFalse(poseeAdnMutante);
    }

}
