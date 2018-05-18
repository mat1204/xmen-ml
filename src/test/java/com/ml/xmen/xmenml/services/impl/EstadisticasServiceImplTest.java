package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.dto.EstadisticasGlobalDTO;
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

import java.math.BigDecimal;

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


//    class SecuenciaADNMock extends SecuenciaADN {
//        private Boolean mutante;
//
//        public SecuenciaADNMock(String secuencias[], Boolean esMutante) {
//            super(secuencias);
//            this.mutante = esMutante;
//        }
//
//        @Override
//        public Boolean esMutante() {
//            return mutante;
//        }
//    }

    @Test
    public void actualizacionEstaisticaNoMutanteTest() {


        EstadisticasGlobalDTO estadisticasGlobalDTO = this.estadisticasService.obtenerEstadisticasGlobales();

        Assert.assertEquals(Long.valueOf(0l) , estadisticasGlobalDTO.getCantidadHumanos());
        Assert.assertEquals(Long.valueOf(0l) , estadisticasGlobalDTO.getCantidadMutantes());
        Assert.assertEquals(BigDecimal.ZERO , estadisticasGlobalDTO.getRatioMutante());

    }
    /*
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


    @Test
    public void obtenerEstadissticasMutanteTest() {

        SecuenciaADN secuenciaADN = new SecuenciaADNMock(Arrays.array("AAT", "BBA", "AAB"), true);

        estadisticasService.actualizarEstadisticas(secuenciaADN);

        EstadisticasGlobalDTO estadisticasGlobalDTO = estadisticasService.obtenerEstadisticasGlobales();

        Assert.assertEquals( Long.valueOf(1), estadisticasGlobalDTO.getCantidadHumanos());
        Assert.assertEquals( Long.valueOf(1), estadisticasGlobalDTO.getCantidadMutantes());
        Assert.assertEquals( new BigDecimal("1.0").doubleValue(), estadisticasGlobalDTO.getRatioMutante().doubleValue(), 0.001d);
    }


    @Test
    public void estadisticasVaciasTest(){
        EstadisticasGlobalDTO estadisticasGlobalDTO = estadisticasService.obtenerEstadisticasGlobales();

        Assert.assertEquals( Long.valueOf(0), estadisticasGlobalDTO.getCantidadHumanos());
        Assert.assertEquals( Long.valueOf(0), estadisticasGlobalDTO.getCantidadMutantes());
        Assert.assertEquals( BigDecimal.ZERO, estadisticasGlobalDTO.getRatioMutante());
    }

    @Test
    public void actualizacionEstaisticaConTipoNoUnicoErrorTest() {

        SecuenciaADN secuenciaADN = new SecuenciaADNMock(Arrays.array("AAT", "BBA", "AAB"), false);

        estadisticasService.actualizarEstadisticas(secuenciaADN);

        Estadistica estadisticaHumanos = estadisticaRepository.findByTipoEstadistica(TipoEstadistica.CANTIDAD_HUMANOS_PROCESADOS);
        Estadistica estadisticaMutantes = estadisticaRepository.findByTipoEstadistica(TipoEstadistica.CANTIDAD_MUTANTES_ENCONTRADOS);

        estadisticaHumanos.setTipoEstadistica(TipoEstadistica.CANTIDAD_MUTANTES_ENCONTRADOS);
        estadisticaHumanos.setValor(29l);

        estadisticaRepository.save(estadisticaHumanos);

    }

    @Test
    public void inicializacionDeEstadisticaTest() {


        Estadistica estadistica = new Estadistica();

        estadistica.setTipoEstadistica(TipoEstadistica.CANTIDAD_HUMANOS_PROCESADOS);
        estadistica.contar();

        estadisticaRepository.save(estadistica);

        Estadistica estRecuperada = estadisticaRepository.findAll().iterator().next();

        Assert.assertEquals(Long.valueOf(1l), estRecuperada.getValor());
        Assert.assertEquals(estadistica.getTipoEstadistica(), estRecuperada.getTipoEstadistica());

    }*/
}
