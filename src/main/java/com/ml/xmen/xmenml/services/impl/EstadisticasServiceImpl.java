package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.dto.EstadisticasGlobalDTO;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import com.ml.xmen.xmenml.services.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class EstadisticasServiceImpl implements EstadisticasService {


    @Autowired
    private RegistroADNRepository registroADNRepository;


    @Override
    @Transactional
    public EstadisticasGlobalDTO obtenerEstadisticasGlobales() {

        Long cantHumanos = registroADNRepository.count();
        Long cantMutantes = registroADNRepository.cantidadDeMutantes();

        BigDecimal ratio = BigDecimal.ZERO;

        if (cantHumanos > 0)
            ratio = BigDecimal.valueOf(cantMutantes)
                    .divide(BigDecimal.valueOf(cantHumanos), 3, RoundingMode.HALF_UP);

        return new EstadisticasGlobalDTO(cantHumanos, cantMutantes, ratio);
    }

}
