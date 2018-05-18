package com.ml.xmen.xmenml.services;

import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.dto.EstadisticasGlobalDTO;

public interface EstadisticasService {

    /**
     * Se consulta por las estadisticas globales de los ADN procesados, cuantos ADN se procesaron
     * y cuantos de estos fueron Mutantes, ademes de un ratio de gen Mutante en ADN.
     * @return informacion de los ADN procesosados
     */
    EstadisticasGlobalDTO obtenerEstadisticasGlobales();

//    /**
//     * Actualiza las estadisticas a partir de una secuencia de candenas de ADN previamente analizada
//     * @param secuenciaADN cadena previamente analiza la poseesion de gen mutante
//     */
//    void actualizarEstadisticas(SecuenciaADN secuenciaADN);

}
