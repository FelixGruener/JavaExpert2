package com.example.jsondemoV2;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Sex {
    @JsonProperty("F")
    FEMALE,
    @JsonProperty("M")
    MALE
}
