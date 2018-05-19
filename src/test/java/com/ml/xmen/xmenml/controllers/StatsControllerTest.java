package com.ml.xmen.xmenml.controllers;

import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.json.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@WebAppConfiguration
public class StatsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;


    @Autowired
    RegistroADNRepository registroADNRepository;

    @Before
    public void inicializarTest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        registroADNRepository.deleteAll();
    }


    @Test
    public void obtenerEstadisticasLimpiasTest() throws Exception {

        Thread.sleep(500l);

        registroADNRepository.deleteAll();
        this.mockMvc
                .perform(get("/stats"))
                .andExpect(result -> {
                    Assert.assertEquals(200, result.getResponse().getStatus());

                    JSONObject jsonResponse = new JSONObject(result.getResponse().getContentAsString());

                    Assert.assertEquals(0l, jsonResponse.getLong("count_human_dna"));
                    Assert.assertEquals(0l, jsonResponse.getLong("count_mutant_dna"));
                    Assert.assertEquals(0d, jsonResponse.getLong("ratio"), 0.001d);

                });
    }


}
