package com.ml.xmen.xmenml.services.impl;

import com.ml.xmen.xmenml.ParametrosADN;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantServiceImpl implements MutantService {

    @Autowired
    private ParametrosADN parametrosADN;

    @Override
    public Boolean isMutant(String[] adn) {

        SecuenciaADN secuenciaADN = new SecuenciaADN(adn);

        return false;
    }

    void persistirSecuenciaAdn(SecuenciaADN secuenciaADN) {

    }

}
