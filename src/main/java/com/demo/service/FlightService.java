package com.demo.service;

import com.demo.persistence.FlightPersistence;
import com.demo.persistence.domain.Destination;
import com.demo.persistence.domain.Flight;
import com.demo.persistence.domain.Ticket;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightPersistence flightPersistence;

    public Boolean checkTicketValidity(Integer ticketId) {
        Ticket ticket = flightPersistence.getTicketById(ticketId);
        if (ticket == null) {
            logger.debug("Ticket ID <" + ticketId + "> not found in the system");
            return false;
        }
        return true;
    }

    public Boolean checkIn(String destinationId, String baggageId) {
        Destination destination = flightPersistence.getDestinationById(destinationId);
        if (destination == null){
            logger.error("Invalid destination: "+ destinationId);
            return false;
        }
        Flight flight = flightPersistence.getFlightByDestination(destinationId);
        if (flight == null) {
            logger.error("No available flights");
            return false;
        }
        Boolean checkInStatus = flightPersistence.addBaggage(baggageId, flight.getId());
        return checkInStatus;
    }

}
