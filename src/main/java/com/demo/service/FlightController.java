package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Controller
public class FlightController {

    @Autowired
    FlightService flightService;

    //check ticket validity
    ResponseEntity<Boolean> checkTicketValidity(@NotNull @RequestParam(value = "ticketId") Integer ticketId) {
        return new ResponseEntity<>(flightService.checkTicketValidity(ticketId), HttpStatus.OK);
    }

    //check in baggage
    ResponseEntity<Boolean> checkIn(@NotNull @RequestParam(value = "destinationId") String destinationId,
                             @NotNull @RequestParam(value = "baggageId") String baggageId) {
        Boolean checkInStatus = flightService.checkIn(destinationId, baggageId);
        //handle checkin status in case of errors and return respective http response
        return new ResponseEntity<>(checkInStatus, HttpStatus.OK);
    }

    //get flight price

    //calls caching

}
