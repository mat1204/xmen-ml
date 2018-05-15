package com.ml.xmen.xmenml.controllers;

import com.ml.xmen.xmenml.ParametrosADN;
import com.ml.xmen.xmenml.controllers.requestbody.ComprobacionMutanteRequest;
import com.ml.xmen.xmenml.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Map;

@Controller
@EnableConfigurationProperties(ParametrosADN.class)
public class MutantController {

    @Autowired
    ParametrosADN parametrosADN;

    @Autowired
    private MutantService mutantService;

    @RequestMapping(value = "/mutant",method = RequestMethod.POST)
    public ResponseEntity consultarADN(@RequestBody ComprobacionMutanteRequest request) {
        return ResponseEntity.ok("OK");
    }

}
