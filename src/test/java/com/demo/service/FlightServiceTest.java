package com.demo.service;

import com.demo.persistence.FlightPersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FlightServiceTest {

    @Autowired
    private FlightPersistence flightPersistence;

    @InjectMocks
    private FlightService flightService;

    @Test
    public void checkValidTicket(){
        assertTrue(flightService.checkTicketValidity(Integer.valueOf(1)));
    }

    @Test
    public void checkInvalidTicket(){
        assertFalse(flightService.checkTicketValidity(Integer.valueOf(2)));
    }

}