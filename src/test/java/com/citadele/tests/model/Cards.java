package com.citadele.tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Cards {

    @JsonProperty("images")
    private List<Images> images;

    @JsonProperty("image")
    private String image;

    @JsonProperty("value")
    private String value;

    @JsonProperty("suit")
    private String suit;

    @JsonProperty("code")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}