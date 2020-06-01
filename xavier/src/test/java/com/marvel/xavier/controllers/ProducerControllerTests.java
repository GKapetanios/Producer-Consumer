package com.marvel.xavier.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.xavier.models.Producer;
import com.marvel.xavier.models.ProducerForm;
import com.marvel.xavier.services.ProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProducerController.class)
class ProducerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProducerService producerService;

    @Test
    void testGetSuccess() throws Exception {
        when(producerService.getProducers()).thenReturn(new LinkedList<>());
        this.mockMvc.perform(get("/producers"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateSuccess() throws Exception {
        ProducerForm fakeForm = new ProducerForm("Wolverine");
        Producer fakeProducer = new Producer("Wolverine");
        ObjectMapper mapper = new ObjectMapper();
        String jsonFakeForm = mapper.writeValueAsString(fakeForm);

        when(producerService.createProducer(fakeForm.getName())).thenReturn(fakeProducer);

        this.mockMvc.perform(post("/producers")
                .content(jsonFakeForm)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
