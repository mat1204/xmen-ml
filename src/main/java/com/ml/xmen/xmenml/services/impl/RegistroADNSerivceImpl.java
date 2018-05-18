package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.config.ParametrosADN;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.entity.RegistroADN;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import com.ml.xmen.xmenml.services.EstadisticasService;
import com.ml.xmen.xmenml.services.MutantService;
import com.ml.xmen.xmenml.services.RegistroADNService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//@Service
public class RegistroADNSerivceImpl implements RegistroADNService {

    @Autowired
    ParametrosADN parametrosADN;

    @Autowired
    RegistroADNRepository registroADNRepository;

    private Logger logger = LogManager.getLogger(RegistroADNSerivceImpl.class);

    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public void persistirSecuenciaAdn(SecuenciaADN secuenciaADN) {

        if (!this.parametrosADN.getPersistirRegistros()) {
            logger.info("Registro no Persistido LUCAS!!!!!");
            return;
        }

        try {
            RegistroADN registroADN = new RegistroADN();

            //logger.info("persistiendo secuencia:" + secuenciaADN.serializarADN());

            registroADN.setSecuenciasADN(secuenciaADN.serializarADN());
            registroADN.setEsMutante(secuenciaADN.esMutante());

            this.registroADNRepository.save(registroADN);
        }
        catch (Exception e) {
            logger.error("Error persistiendo Secuencia de tamanio: " + secuenciaADN.serializarADN().length());
            logger.error(e);
        }

    }
}
