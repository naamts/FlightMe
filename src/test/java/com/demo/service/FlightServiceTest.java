package com.demo.service;

import com.demo.App;
import com.demo.persistence.FlightPersistence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {App.class})
public class FlightServiceTest {

    @Autowired
    private FlightPersistence flightPersistence;

    @Autowired
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
