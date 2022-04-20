package com.citadele.tests.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {
    @JsonProperty("svg")
    private String svg;

    @JsonProperty("png")
    private String png;
}