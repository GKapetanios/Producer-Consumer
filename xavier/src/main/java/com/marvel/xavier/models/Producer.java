package com.marvel.xavier.models;

import java.util.UUID;

public class Producer {
    private String id;
    private String name;

    public Producer() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
    }

    public Producer(String name) {
        this();
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Method that produces a new task
     *
     * @return The produced task
     */
    public Task produceTask() {
        return new Task(this.getId());
    }
}
