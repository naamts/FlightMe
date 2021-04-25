package com.demo.persistence.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends NilsObject{
    @JsonProperty("destinationId")
    private String destinationId;
    @JsonProperty("departureDate")
    private Date departureDate;
}
