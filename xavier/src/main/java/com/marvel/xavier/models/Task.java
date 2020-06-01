package com.marvel.xavier.models;

import java.util.UUID;

public class Task {
    private String id;
    private String producerId;

    public Task() {
        this.id = UUID.randomUUID().toString();
        this.producerId = "";
    }

    public Task(String producerId) {
        this();
        this.producerId = producerId;
    }

    public String getId() {
        return this.id;
    }

    public String getProducerId() {
        return this.producerId;
    }
}
