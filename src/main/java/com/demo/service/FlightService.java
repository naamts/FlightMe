package com.demo.service;

import com.demo.persistence.FlightPersistence;
import com.demo.persistence.domain.Destination;
import com.demo.persistence.domain.Flight;
import com.demo.persistence.domain.Ticket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            logger.debug(String.format("Ticket ID <%d> not found in the system", ticketId));
            return false;
        }
        return true;
    }

    public Boolean checkIn(String destinationId, String baggageId) {
        Destination destination = flightPersistence.getDestinationById(destinationId);
        if (destination == null){
            logger.error(String.format("Invalid destination: %s", destinationId));
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
