package com.marvel.xavier.controllers;

import com.marvel.xavier.models.Producer;
import com.marvel.xavier.models.ProducerForm;
import com.marvel.xavier.services.ProducerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProducerController {
    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping(
            value = "/producers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Producer> get() {
        return producerService.getProducers();
    }

    @PostMapping(
            value = "/producers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Producer create(@RequestBody ProducerForm producerForm) {
        Producer producer = producerService.createProducer(producerForm.getName());

        return producer;
    }
}
