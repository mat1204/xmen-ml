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

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    ComprobadorADN comprobadorADN;

    @Autowired
    RegistroADNService registroADNService;

    @Autowired
    EstadisticasService estadisticasService;

    @Override
    public Boolean isMutant(String[] adn) {

        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean esMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        this.registroADNService.persistirSecuenciaAdn(secuenciaADN);

        this.estadisticasService.actualizarEstadisticas(secuenciaADN);

        return esMutante;
    }


}
