package com.ml.xmen.xmenml.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class StatsController {

    @RequestMapping(value = "/stats",method = RequestMethod.GET)
    public ResponseEntity obtenerEstadisticas() {

        Map result = new HashMap();

        result.put("count_mutant_dna", 21);
        result.put("count_human_dna", 2);
        result.put("ratio", 0.2f);

        return ResponseEntity.ok(result);
    }

}
