package com.ml.xmen.xmenml.controllers;

import com.ml.xmen.xmenml.repository.EstadisticaRepository;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@WebAppConfiguration
public class MutantControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void inicializarTest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void requestAdnMutanteTest() throws Exception {

        String jsonRequest ="{ \"dna\": [ \"AABB\", \"AABB\", \"AAAB\", \"AABA\"] }";

        this.mockMvc
                .perform(post("/mutant").content(jsonRequest).contentType("application/json"))
                .andExpect(result -> {
                    Assert.assertEquals(200, result.getResponse().getStatus());
                });
    }


    @Test
    public void requestAdnNoMutanteTest() throws Exception {

        String jsonRequest ="{ \"dna\": [ \"AT\", \"BA\" ] }";

        this.mockMvc
                .perform(post("/mutant").content(jsonRequest).contentType("application/json"))
                .andExpect(result -> {
                    Assert.assertEquals(403, result.getResponse().getStatus());
                });
    }

    @Test
    public void requestConJSONMalFormadoTest() throws Exception {

        String jsonRequest ="{ \"dna\": [] }";

        this.mockMvc
                .perform(post("/mutant").content(jsonRequest).contentType("application/json"))
                .andExpect(result -> {
                    Assert.assertEquals(400, result.getResponse().getStatus());
                });
    }


    @Test
    public void requestConJSONMalFormadoConCadenaVaciaTest() throws Exception {

        String jsonRequest ="{ \"dna\": [ \"ABC\",\"ABC\", \"\"] }";

        this.mockMvc
                .perform(post("/mutant").content(jsonRequest).contentType("application/json"))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void requestConJSONVacioTest() throws Exception {

        String jsonRequest ="{ }";

        this.mockMvc
                .perform(post("/mutant").content(jsonRequest).contentType("application/json"))
                .andExpect(status().isBadRequest());

    }

}
