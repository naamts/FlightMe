package com.demo.persistence;

import com.demo.persistence.domain.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class FlightPersistence {

    private static final String BASE_PATH = "src/main/resources/";

    Map<String, Ticket> ticketMap;
    Map<String, Flight> flightMap;
    Map<String, Destination> destinationMap;
    Map<String, Baggage> baggageMap = new HashMap<>();

    public Ticket getTicketById(Integer ticketId) {
        if (CollectionUtils.isEmpty(ticketMap)) {
            ticketMap = loadData("tickets", Ticket.class);
        }

        return ticketMap.get(ticketId.toString());
    }

    public Flight getFlightByDestination(String destinationId) {
        if (CollectionUtils.isEmpty(flightMap)) {
            flightMap = loadData("flights", Flight.class);
        }

        return flightMap.values().stream().filter(f -> f.getDestinationId().equals(destinationId)).findFirst().orElse(null);
    }

    public Boolean addBaggage(String baggageId, String flightId) {
        Baggage b = new Baggage(baggageId, flightId, Integer.valueOf(1));
        baggageMap.put(baggageId, b);

        return true;
    }

    public Destination getDestinationById(String destinationId) {
        if (CollectionUtils.isEmpty(destinationMap)) {
            destinationMap = loadData("destinations", Destination.class);
        }

        return destinationMap.get(destinationId);
    }

    private <T extends NilsObject> Map<String, T> loadData(String path, Class<T> objectClass) {
        try {
            List<Path> filePaths = Files.list(Paths.get(BASE_PATH + path)).map(Path::toAbsolutePath).collect(Collectors.toList());
            return (Map<String, T>) filePaths.stream().map(filePath -> readFile(filePath, objectClass)).collect(Collectors.toMap(NilsObject::getId, Function.identity()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private <T extends NilsObject> NilsObject readFile(Path filePath, Class<T> objectClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NilsObject t = objectMapper.readValue(Files.readAllBytes(filePath), objectClass);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
