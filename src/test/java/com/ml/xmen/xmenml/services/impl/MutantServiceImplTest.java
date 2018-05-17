package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.entity.RegistroADN;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import org.junit.Assert;
import org.junit.Before;
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

    @Autowired
    RegistroADNRepository registroADNRepository;

    @Before
    public void inicializarTest() {
        this.registroADNRepository.deleteAll();
    }

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


    @Test
    public void secuenciaMutantePersistidaTest() {
        String[] adn = {
                "ACCCA",
                "CACAA",
                "CAACA",
                "CACAA",
                "ACAAA"};

        Boolean esMutante = mutantService.isMutant(adn);

        Assert.assertEquals(1l, registroADNRepository.count());

        RegistroADN registroADN = registroADNRepository.findAll().iterator().next();

        Assert.assertNotNull(registroADN.getId());
        Assert.assertEquals("['ACCCA','CACAA','CAACA','CACAA','ACAAA']",registroADN.getSecuenciasADN());
        Assert.assertTrue(registroADN.getEsMutante());
    }

    @Test
    public void secuenciaNoMutantePersistidaTest() {
        String[] adn = {
                "ACCCA",
                "CACAC",
                "CACCC",
                "CCCAC",
                "ACAAA"};

        Boolean esMutante = mutantService.isMutant(adn);

        Assert.assertEquals(1l, registroADNRepository.count());

        RegistroADN registroADN = registroADNRepository.findAll().iterator().next();

        Assert.assertNotNull(registroADN.getId());
        Assert.assertEquals("['ACCCA','CACAC','CACCC','CCCAC','ACAAA']",registroADN.getSecuenciasADN());
        Assert.assertFalse(registroADN.getEsMutante());
    }

}
