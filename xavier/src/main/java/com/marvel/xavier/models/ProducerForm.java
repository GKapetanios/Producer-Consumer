package com.marvel.xavier.models;

public class ProducerForm {
    private String name;

    public ProducerForm(){
        this.name = "";
    }

    public ProducerForm(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
