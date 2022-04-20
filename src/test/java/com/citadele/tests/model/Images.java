package com.citadele.tests.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Images {
    @JsonProperty("svg")
    private String svg;

    @JsonProperty("png")
    private String png;
}