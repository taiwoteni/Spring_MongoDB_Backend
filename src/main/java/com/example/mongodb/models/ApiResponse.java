package com.example.mongodb.models;

import com.google.gson.Gson;

public class ApiResponse {

    private String message;

    private Object data;

    public ApiResponse(){}

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this, ApiResponse.class);
    }
}
