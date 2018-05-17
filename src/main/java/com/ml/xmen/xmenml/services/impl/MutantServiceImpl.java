package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.config.ParametrosADN;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.entity.RegistroADN;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import com.ml.xmen.xmenml.services.EstadisticasService;
import com.ml.xmen.xmenml.services.MutantService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    ComprobadorADN comprobadorADN;

    @Autowired
    ParametrosADN parametrosADN;

    @Autowired
    RegistroADNRepository registroADNRepository;

    @Autowired
    EstadisticasService estadisticasService;

    private Logger logger = LogManager.getLogger(MutantService.class);

    @Override
    @Transactional
    public Boolean isMutant(String[] adn) {

        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean esMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        this.persistirSecuenciaAdn(secuenciaADN);

        this.estadisticasService.actualizarEstadisticas(secuenciaADN);

        return esMutante;
    }


    private void persistirSecuenciaAdn(SecuenciaADN secuenciaADN) {

        if (!this.parametrosADN.getPersistirRegistros())
            return;

        try {
            RegistroADN registroADN = new RegistroADN();

            //logger.info("persistiendo secuencia:" + secuenciaADN.serializarADN());

            registroADN.setSecuenciasADN(secuenciaADN.serializarADN());
            registroADN.setEsMutante(secuenciaADN.esMutante());

            this.registroADNRepository.save(registroADN);
        }
        catch (Exception e) {
            logger.error("Error persistiend Secuencia: " + secuenciaADN.serializarADN());
            logger.error(e);
        }

    }

}
