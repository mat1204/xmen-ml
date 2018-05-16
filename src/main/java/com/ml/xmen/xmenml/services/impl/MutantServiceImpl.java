package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.comprobadores.ComprobadorADN;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    ComprobadorADN comprobadorADN;

    @Override
    public Boolean isMutant(String[] adn) {

        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        Boolean esMutante = secuenciaADN.contieneAdnMutante(comprobadorADN);

        this.persistirSecuenciaAdn(secuenciaADN);

        return esMutante;
    }

    void persistirSecuenciaAdn(SecuenciaADN secuenciaADN) {
        // TODO
    }

}
