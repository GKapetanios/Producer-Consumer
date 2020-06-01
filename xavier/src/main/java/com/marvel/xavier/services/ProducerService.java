package com.marvel.xavier.services;

import com.marvel.xavier.models.Producer;

import java.util.List;

public interface ProducerService {
    List<Producer> getProducers();

    Producer createProducer(String producerName);
}
