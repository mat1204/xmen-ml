package com.ml.xmen.xmenml.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MutantController {

    @RequestMapping(value = "/mutant",method = RequestMethod.POST)
    public ResponseEntity consultarADN(@RequestBody Map body) {
        return ResponseEntity.ok("OK");
    }

}
