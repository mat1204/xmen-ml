package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.entity.Estadistica;
import com.ml.xmen.xmenml.entity.TipoEstadistica;
import com.ml.xmen.xmenml.repository.EstadisticaRepository;
import org.assertj.core.util.Arrays;
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
public class EstadisticasServiceImplTest {


    @Autowired
    EstadisticaRepository estadisticaRepository;
    @Autowired
    EstadisticasServiceImpl estadisticasService;


    @Before
    public void inicializarTest() {
        estadisticaRepository.deleteAll();
    }


    class SecuenciaADNMock extends SecuenciaADN {
        private Boolean mutante;

        public SecuenciaADNMock(String secuencias[], Boolean esMutante) {
            super(secuencias);
            this.mutante = esMutante;
        }

        @Override
        public Boolean esMutante() {
            return mutante;
        }
    }

    @Test
    public void actualizacionEstaisticaNoMutanteTest() {

        SecuenciaADN secuenciaADN = new SecuenciaADNMock(Arrays.array("AAT", "BBA", "AAB"), false);

        estadisticasService.actualizarEstadisticas(secuenciaADN);

        Estadistica estadisticaHumanos = estadisticaRepository.findByTipoEstadistica(TipoEstadistica.CANTIDAD_HUMANOS_PROCESADOS);
        Estadistica estadisticaMutantes = estadisticaRepository.findByTipoEstadistica(TipoEstadistica.CANTIDAD_MUTANTES_ENCONTRADOS);

        Assert.assertNotNull(estadisticaHumanos);
        Assert.assertEquals(TipoEstadistica.CANTIDAD_HUMANOS_PROCESADOS, estadisticaHumanos.getTipoEstadistica());
        Assert.assertNull(estadisticaMutantes);


        Assert.assertEquals( 1, estadisticaRepository.count());
        Assert.assertEquals( Long.valueOf(1), estadisticaHumanos.getValor());

    }

    @Test
    public void actualizacionEstaisticaMutanteTest() {

        SecuenciaADN secuenciaADN = new SecuenciaADNMock(Arrays.array("AAT", "BBA", "AAB"), true);

        estadisticasService.actualizarEstadisticas(secuenciaADN);

        Estadistica estadisticaHumanos = estadisticaRepository.findByTipoEstadistica(TipoEstadistica.CANTIDAD_HUMANOS_PROCESADOS);
        Estadistica estadisticaMutantes = estadisticaRepository.findByTipoEstadistica(TipoEstadistica.CANTIDAD_MUTANTES_ENCONTRADOS);

        Assert.assertNotNull(estadisticaHumanos);
        Assert.assertNotNull(estadisticaMutantes);
        Assert.assertEquals(TipoEstadistica.CANTIDAD_MUTANTES_ENCONTRADOS, estadisticaMutantes.getTipoEstadistica());

        Assert.assertEquals( 2, estadisticaRepository.count());

        Assert.assertEquals( Long.valueOf(1), estadisticaHumanos.getValor());
        Assert.assertEquals( Long.valueOf(1), estadisticaMutantes.getValor());

    }

}
