package com.readapp.backend.models.events;

import java.io.Serializable;

public class Event implements Serializable {
    private String type;
    private Object data;

    public String getType() {
        return type;
    }

    public Event setType(String type) {
        this.type = type;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Event setData(Object data) {
        this.data = data;
        return this;
    }
}
