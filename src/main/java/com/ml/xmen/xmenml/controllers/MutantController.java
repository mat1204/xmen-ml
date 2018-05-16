package com.ml.xmen.xmenml.controllers;

import com.ml.xmen.xmenml.config.ParametrosADN;
import com.ml.xmen.xmenml.controllers.requestbody.ComprobacionMutanteRequest;
import com.ml.xmen.xmenml.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@EnableConfigurationProperties(ParametrosADN.class)
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @RequestMapping(value = "/mutant",method = RequestMethod.POST)
    public ResponseEntity consultarADN(@RequestBody ComprobacionMutanteRequest request) {

        Boolean esMutante = mutantService.isMutant(request.getDna());

        if (esMutante)
            return ResponseEntity.ok().build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
