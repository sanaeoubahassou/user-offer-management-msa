package com.example.projectpfe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DataToken {
    @JsonProperty("token")
    private String token;
}
