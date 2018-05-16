package com.ml.xmen.xmenml.controllers;

import com.ml.xmen.xmenml.config.ParametrosADN;
import com.ml.xmen.xmenml.controllers.requestbody.ComprobacionMutanteRequest;
import com.ml.xmen.xmenml.services.MutantService;
import com.ml.xmen.xmenml.validators.ComprobacionMutanteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@EnableConfigurationProperties(ParametrosADN.class)
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @Autowired
    private ComprobacionMutanteValidator comprobacionMutanteValidator;

    @InitBinder("comprobacionMutanteRequest")
    void setupBinder(WebDataBinder binder) {
        binder.addValidators(this.comprobacionMutanteValidator);
    }

    @RequestMapping(value = "/mutant",method = RequestMethod.POST)
    public ResponseEntity consultarADN(@Valid @RequestBody ComprobacionMutanteRequest comprobacionMutanteRequest) {//, Errors errors) {

        Boolean esMutante = mutantService.isMutant(comprobacionMutanteRequest.getDna());

        if (esMutante)
            return ResponseEntity.ok().build();

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
