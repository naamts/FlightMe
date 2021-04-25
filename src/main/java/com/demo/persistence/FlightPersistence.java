package com.demo.persistence;

import com.demo.persistence.domain.*;
import com.demo.service.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FlightPersistence {

    private static final String BASE_PATH = "src/test/resources/";
    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);

    Map<String, Ticket> ticketMap;
    Map<String, Flight> flightMap;
    Map<String, Destination> destinationMap;
    Map<String, Baggage> baggageMap = new HashMap<>();

    public Ticket getTicketById(Integer ticketId) {
        if (CollectionUtils.isEmpty(ticketMap)) {
            loadData("tickets", Ticket.class, ticketMap);
        }

        return ticketMap.get(ticketId.toString());
    }

    public Flight getFlightByDestination(String destinationId) {
        if (CollectionUtils.isEmpty(flightMap)) {
            loadData("flights", Flight.class, flightMap);
        }

        return flightMap.values().stream().filter(f -> f.getDestinationId().equals(destinationId)).findFirst().orElse(null);
    }

    public Boolean addBaggage(String baggageId, String flightId) {
        Baggage b = Baggage.builder().flightId(flightId).suitcaseCounter(1).build();
        b.setId(baggageId);
        baggageMap.put(baggageId, b);

        return true;
    }

    public Destination getDestinationById(String destinationId) {
        if (CollectionUtils.isEmpty(destinationMap)) {
            loadData("destinations", Destination.class, destinationMap);
        }

        return destinationMap.get(destinationId);
    }

    private <T extends NilsObject> void loadData(String path, Class<T> objectClass, Map<String, T> dataMap) {
        try {
            List<Path> filePaths = Files.list(Paths.get(BASE_PATH + path)).map(Path::toAbsolutePath).collect(Collectors.toList());
            filePaths.forEach(filePath -> readFile(filePath, objectClass, dataMap));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private <T extends NilsObject> void readFile(Path filePath, Class<T> objectClass, Map<String, T> dataMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NilsObject t = objectMapper.readValue(Files.readAllBytes(filePath), objectClass);
            dataMap.put(t.getId(), (T) t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
