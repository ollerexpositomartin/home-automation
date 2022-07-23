package com.moex.homeAutomation.domain.models;


public class Action {

    enum States {
        ON,OFF
    }

    private String id;
    private String value;

    private Action() {}

    public Action(String id,String value){
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
