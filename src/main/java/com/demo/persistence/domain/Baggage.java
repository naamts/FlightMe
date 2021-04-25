package com.demo.persistence.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Baggage extends NilsObject{
    @JsonProperty("flightId")
    private String flightId;
    @JsonProperty("suitcaseCounter")
    private Integer suitcaseCounter;

    public Baggage(String id, String flightId, Integer suitCaseCount) {
        super(id);
        this.flightId = flightId;
        this.suitcaseCounter = suitCaseCount;
    }
}

