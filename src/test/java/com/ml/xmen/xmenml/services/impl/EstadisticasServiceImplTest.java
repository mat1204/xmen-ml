package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.dto.EstadisticasGlobalDTO;
import com.ml.xmen.xmenml.entity.RegistroADN;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EstadisticasServiceImplTest {


    @Autowired
    EstadisticasServiceImpl estadisticasService;

    @Autowired
    RegistroADNRepository registroADNRepository;

    @Before
    public void inicializar() {
        registroADNRepository.deleteAll();
    }

    @After
    public void limpiar() {
        registroADNRepository.deleteAll();
    }

    @Test
    public void estadisticasLimpiasTest() {

        EstadisticasGlobalDTO estadisticasGlobalDTO = this.estadisticasService.obtenerEstadisticasGlobales();

        Assert.assertEquals(Long.valueOf(0l) , estadisticasGlobalDTO.getCantidadHumanos());
        Assert.assertEquals(Long.valueOf(0l) , estadisticasGlobalDTO.getCantidadMutantes());
        Assert.assertEquals(BigDecimal.ZERO , estadisticasGlobalDTO.getRatioMutante());

    }


    @Test
    public void estadisticasConMutanteTest() {

        RegistroADN registroADN = new RegistroADN();
        registroADN.setEsMutante(true);
        registroADN.setSecuenciasADN("ABC,ABC,ABC");
        registroADNRepository.save(registroADN);


        EstadisticasGlobalDTO estadisticasGlobalDTO = this.estadisticasService.obtenerEstadisticasGlobales();

        Assert.assertEquals(Long.valueOf(1l) , estadisticasGlobalDTO.getCantidadHumanos());
        Assert.assertEquals(Long.valueOf(1l) , estadisticasGlobalDTO.getCantidadMutantes());
        Assert.assertEquals(BigDecimal.ONE.doubleValue() , estadisticasGlobalDTO.getRatioMutante().doubleValue(), 0.001d);

    }
}
