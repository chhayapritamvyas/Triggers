package com.notes.app.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.app.domain.Trigger;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
class TriggerControllerTest {

    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setup(){
//        DefaultMockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(webApplicationContext);
//    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void addTriggerPoints() throws Exception {
        String uri = "";
        String input = mapToJson(new Trigger(1L, "notes are useful", "new"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn();

        assertEquals(201,mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Added trigger points successfully"));

    }

    @Test
    void saveTriggers() throws Exception {
        String uri = "/triggers";
        String input = mapToJson(List.of(new Trigger(1L, "notes are useful parts", "new")));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn();

        assertEquals(200,mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Saved Triggers"));

    }

    @Test
    void getTriggerPoints() {
    }

    @Test
    void getTriggersById() {
    }

    @Test
    void updateTriggers() {
    }

    @Test
    void deleteTriggers() {
    }
}