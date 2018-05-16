package com.ml.xmen.xmenml.services;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.dto.EstadisticasGlobalDTO;

public interface EstadisticasService {

    EstadisticasGlobalDTO obtenerEstadisticasGlobales();

    void actualizarEstadisticas(SecuenciaADN secuenciaADN);

}
