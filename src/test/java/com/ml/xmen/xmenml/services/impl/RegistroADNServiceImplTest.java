package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.entity.RegistroADN;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RegistroADNServiceImplTest {

    @Autowired
    RegistroADNSerivceImpl registroADNSerivce;

    @Autowired
    RegistroADNRepository registroADNRepository;

    @Before
    public void inicializarTest() {
        this.registroADNRepository.deleteAll();
    }

    @After
    public void limpiarTest() {
        this.registroADNRepository.deleteAll();
    }


    public class SecuenciaADNMock extends SecuenciaADN {
        private Boolean mutante;

        public SecuenciaADNMock(String[] secuenciasAdn) {
            super(secuenciasAdn);
            this.mutante = true;
        }

        public SecuenciaADNMock(String[] secuenciasAdn, Boolean mutante) {
            this(secuenciasAdn);
            this.mutante = mutante;
        }

        @Override
        public Boolean esMutante() {
            return this.mutante;
        }

        @Override
        public Boolean secuenciaAnalizada() {
            return true;
        }
    }


    @Test
    public void persistenciaRegistroTest() {
        SecuenciaADN secuenciaADN = new SecuenciaADNMock(org.assertj.core.util.Arrays.array("ABT", "ABT", "ABT"), true);

        this.registroADNSerivce.persistirSecuenciaAdn(secuenciaADN);

        Assert.assertEquals(1l, registroADNRepository.cantidadDeMutantes().longValue());
        Assert.assertEquals(0l, registroADNRepository.cantidadDeHumanosNoMutantes().longValue());

        Assert.assertEquals(1l, registroADNRepository.count());
        RegistroADN registroADN = registroADNRepository.findAll().iterator().next();

        Assert.assertEquals(true, registroADN.getEsMutante());
        Assert.assertEquals("['ABT','ABT','ABT']", registroADN.getSecuenciasADN());

        registroADN.setId(1l);

    }
}
