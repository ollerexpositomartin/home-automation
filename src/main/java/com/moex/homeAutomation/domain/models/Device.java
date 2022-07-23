package com.moex.homeAutomation.domain.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Device {
    @Id
    private String id;
    private int pin;
    private String value;

    public Device(String id, int pin) {
        this.id = id;
        this.pin = pin;
    }

    public Device(String id, int pin, String value) {
        this.id = id;
        this.pin = pin;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
