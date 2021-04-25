package com.demo.persistence.domain;

import lombok.Builder;

@Builder
public class Baggage extends NilsObject{
    private String flightId;
    private Integer suitcaseCounter;
}
