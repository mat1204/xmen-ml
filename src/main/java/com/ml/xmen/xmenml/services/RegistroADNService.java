package com.ml.xmen.xmenml.services;

import com.ml.xmen.xmenml.domain.SecuenciaADN;

/**
 * Servicio utilizado para persistir en la base de datos las secuencias de ADN analizadas
 */
public interface RegistroADNService {

    /**
     * Persiste una secuancia de ADN
     * @param secuenciaADN secuencia de ADN previamente analizada
     */
    void persistirSecuenciaAdn(SecuenciaADN secuenciaADN);

}
