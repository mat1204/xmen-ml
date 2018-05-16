package com.ml.xmen.xmenml.controllers;

import com.ml.xmen.xmenml.dto.EstadisticasGlobalDTO;
import com.ml.xmen.xmenml.services.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StatsController {


    @Autowired
    private EstadisticasService estadisticasService;

    @RequestMapping(value = "/stats",method = RequestMethod.GET)
    public ResponseEntity obtenerEstadisticas() {

        /*
        Map result = new HashMap();

        result.put("count_mutant_dna", 21);
        result.put("count_human_dna", 2);
        result.put("ratio", 0.2f);
        */

        EstadisticasGlobalDTO estadisticaDTO = estadisticasService.obtenerEstadisticasGlobales();


        return ResponseEntity.ok(estadisticaDTO);
    }

}
