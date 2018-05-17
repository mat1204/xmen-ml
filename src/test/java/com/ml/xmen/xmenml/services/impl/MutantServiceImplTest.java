package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MutantServiceImplTest {

    @Autowired
    MutantServiceImpl mutantService;


    @Test
    public void secuenciaMutanteTest() {
        String[] adn = {
                "ACCCCCCCCCCCC",
                "CACCCACCCCCCC",
                "CCACCCACCCCCC",
                "CCCACCCACCCCC",
                "CCCCACCCACCCC",
                "CCCCCACCCACCC",
                "CCCCCCCCCCACC",
                "CCCCCCCACCCAC",
                "CCCCCCCCACCCA",
                "CCCCCCCCCACCC",
                "CCCCCCCCCCACC",
                "CCCCCCCCCCCAC",
                "CCCCCCCCCCCCA"};

        Boolean esMutante = mutantService.isMutant(adn);

        Assert.assertTrue(esMutante);
    }


    @Test
    public void secuenciaNoMutanteTest() {
        String[] adn = {
                "ACCCCCCCCCCCC",
                "CACCCACCCCCCC",
                "CCACCCACCCCCC",
                "CCCCCCCCCCCCC",
                "CCCCACCCACCCC",
                "CCCCCACCCCCCC",
                "ACCCCCCCCCACC",
                "CACCCCCCCCCAC",
                "CCACCCCCCCCCA",
                "CCCCCCCCCCCCC",
                "CCCCACCCCCCCC",
                "CCCCCACCCCCCC",
                "CCCCCCCCCCCCC"};

        Boolean esMutante = mutantService.isMutant(adn);

        Assert.assertFalse(esMutante);
    }



    @Test
    public void secuenciaMutante2Test() {
        String[] adn = {
                "ACCCCCCCCCCCC",
                "CACCCACCCCCCC",
                "CCACCCACCCCCC",
                "CCAAAAAAACCCC",
                "CCCCCCCCACCCC",
                "CCCCCCCCCACCC",
                "ACCCCCCCCCACC",
                "CACCCCCCCCCAC",
                "CCACCCCCCCCCA",
                "CCCACCCCCCCCC",
                "CCCCACCCCCCCC",
                "CCCCCACCCCCCC",
                "CCCCCCCCCCCCC"};

        Boolean esMutante = mutantService.isMutant(adn);

        Assert.assertTrue(esMutante);
    }

}
