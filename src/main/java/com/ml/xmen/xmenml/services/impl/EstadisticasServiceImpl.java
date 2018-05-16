package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.dto.EstadisticasGlobalDTO;
import com.ml.xmen.xmenml.entity.Estadistica;
import com.ml.xmen.xmenml.entity.TipoEstadistica;
import com.ml.xmen.xmenml.repository.EstadisticaRepository;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import com.ml.xmen.xmenml.services.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {

    @Autowired
    private EstadisticaRepository estadisticaRepository;

    @Override
    @Transactional
    public EstadisticasGlobalDTO obtenerEstadisticasGlobales() {

        Long cantHumanos = obtenerEstadistica(TipoEstadistica.CANTIDAD_HUMANOS_PROCESADOS).getValor();
        Long cantMutantes = obtenerEstadistica(TipoEstadistica.CANTIDAD_MUTANTES_ENCONTRADOS).getValor();

        BigDecimal ratio = BigDecimal.ZERO;

        if (cantHumanos > 0)
            ratio = BigDecimal.valueOf(cantMutantes)
                    .divide(BigDecimal.valueOf(cantHumanos), 3, RoundingMode.HALF_UP);

        return new EstadisticasGlobalDTO(cantHumanos, cantMutantes, ratio);
    }

    @Override
    @Transactional
    public void actualizarEstadisticas(SecuenciaADN secuenciaADN) {
        Estadistica cantHumanos = obtenerEstadistica(TipoEstadistica.CANTIDAD_HUMANOS_PROCESADOS);
        cantHumanos.contar();
        estadisticaRepository.save(cantHumanos);

        if (secuenciaADN.esMutante()) {
            Estadistica cantMutantes = obtenerEstadistica(TipoEstadistica.CANTIDAD_MUTANTES_ENCONTRADOS);
            cantMutantes.contar();
            estadisticaRepository.save(cantMutantes);
        }
    }

    private Estadistica obtenerEstadistica(TipoEstadistica tipoEstadistica) {
        Estadistica estadistica = estadisticaRepository.findByTipoEstadistica(tipoEstadistica);

        if ( estadistica == null)
            estadistica = new Estadistica(tipoEstadistica);

        return estadistica;
    }
}
