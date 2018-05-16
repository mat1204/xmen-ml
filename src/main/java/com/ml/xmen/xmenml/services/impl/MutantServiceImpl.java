package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.entity.RegistroADN;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import com.ml.xmen.xmenml.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    ComprobadorADN comprobadorADN;

    @Autowired
    RegistroADNRepository registroADNRepository;

    @Override
    @Transactional
    public Boolean isMutant(String[] adn) {

        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean esMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        this.persistirSecuenciaAdn(secuenciaADN);

        return esMutante;
    }


    private void persistirSecuenciaAdn(SecuenciaADN secuenciaADN) {
        RegistroADN registroADN = new RegistroADN();

        registroADN.setSecuenciasADN(secuenciaADN.serializarADN());
        registroADN.setEsMutante(secuenciaADN.getEsMutante());

        this.registroADNRepository.save(registroADN);
    }

}
