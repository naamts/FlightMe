package com.demo.service;

import com.demo.persistence.FlightPersistence;
import com.demo.persistence.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

//    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightPersistence flightPersistence;

    public Boolean checkTicketValidity(Integer ticketId) {
        Ticket ticket = flightPersistence.getTicketById(ticketId);
        if (ticket == null) {
//            logger.debug("Ticket ID <" + ticketId + "> not found in the system");
            return false;
        }
        return true;
    }

    public Boolean checkIn(String destinationId, String baggageId) {
        Boolean checkInStatus = flightPersistence.addBaggage(destinationId, baggageId);
        return checkInStatus;
    }

}
