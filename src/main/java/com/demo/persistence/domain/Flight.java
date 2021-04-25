package com.demo.persistence.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Flight extends NilsObject{
    private String destinationId;
    private Date departureDate;

    Flight(String Id) {
        super(Id);
    }
}
