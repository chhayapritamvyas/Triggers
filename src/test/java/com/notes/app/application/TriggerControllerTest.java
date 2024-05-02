package com.notes.app.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.app.domain.Trigger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class TriggerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void addTriggerPoints() throws Exception {
        String uri = "";
        String input = mapToJson(new Trigger(1L, "notes are useful", "new"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn();

        assertEquals(201, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Added trigger points successfully"));

    }

    @Test
    void saveTriggers() throws Exception {
        String uri = "/triggers";
        String input = mapToJson(List.of(new Trigger(1L, "notes are useful parts", "new")));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Saved Triggers"));

    }

    @Test
    void getTriggerPoints() throws Exception {
        String uri = "";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String responseStr = mvcResult.getResponse().getContentAsString();
        assertEquals(200, response.getStatus());
        List<Trigger> triggerList = new ObjectMapper().readValue(responseStr, new TypeReference<>() {
        });
        assertFalse(triggerList.isEmpty());
        assertTrue(response.getContentAsString().contains("notes are useful parts"));

    }

    @Test
    void getTriggersById() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/triggers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void updateTriggers() throws Exception {
        String uri = "/triggers/update";
        String input = mapToJson(new Trigger(1L, "notes are useful", "new"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Updated Triggers successfully"));

    }

    @Test
    void deleteTriggers() throws Exception {
        String uri = "/triggers/delete";
        String input = mapToJson(new Trigger(1L, "notes are useful", "new"));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Deleted Triggers successfully"));
    }
}