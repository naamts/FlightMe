package com.demo.persistence;

import com.demo.persistence.domain.Flight;
import com.demo.persistence.domain.NilsObject;
import com.demo.persistence.domain.Ticket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FlightPersistence {

    private static final String BASE_PATH = "src/test/resources/";


    Map<String, Ticket> ticketMap;
    Map<String, Flight> flightMap;

    public Ticket getTicketById(Integer ticketId) {
        if (CollectionUtils.isEmpty(ticketMap)) {
            loadData("tickets",Ticket.class, ticketMap);
        }

        return ticketMap.get(ticketId.toString());
    }

    public Flight getFlightById(String flightId) {
        if (CollectionUtils.isEmpty(flightMap)) {
            loadData("flights",Flight.class, flightMap);
        }

        return flightMap.get(flightId);
    }

    private <T extends NilsObject> void loadData(String path, Class<T> objectClass, Map<String, T> dataMap) {
        try {
            List<Path> filePaths = Files.list(Paths.get(BASE_PATH + path)).map(Path::toAbsolutePath).collect(Collectors.toList());
            filePaths.forEach(filePath->readFile(filePath, objectClass, dataMap));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private <T extends NilsObject> void readFile(Path filePath, Class<T> objectClass, Map<String,T> dataMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NilsObject t = objectMapper.readValue(Files.readAllBytes(filePath), objectClass);
            dataMap.put(t.getId(), (T) t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean addBaggage(String destinationId, String baggageId) {
        return true;
    }
}
