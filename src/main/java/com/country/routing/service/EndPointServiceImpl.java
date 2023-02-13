package com.country.routing.service;

import com.country.routing.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.*;

@Service
public class EndPointServiceImpl implements EndPointService {

    private static final String DEVELOPER_TEST_URL = "https://raw.githubusercontent.com/mledoze/countries/master/countries.json";

    private Map<String, Country> loadJSONData() {
        URL url;
        List<Country> countryList;
        try {
            url = new URL(DEVELOPER_TEST_URL);
            ObjectMapper mapper = new ObjectMapper();
            countryList = mapper.readValue(url, new TypeReference<List<Country>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Country> countries = new HashMap<>();
        countryList.forEach(country -> countries.put(country.getCca3(), country));
        return countries;
    }

    public List<String> calculateRoute(String origin, String destination) {

        Map<String, Country> countries = loadJSONData();

        Queue<String> queue = new LinkedList<>();
        Map<String, String> visited = new HashMap<>();
        queue.offer(origin);
        visited.put(origin, null);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            Country currentCountry = countries.get(current);
            if (currentCountry == null) {
                continue;
            }

            for (String neighbor : currentCountry.getNeighbors()) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, current);
                    queue.offer(neighbor);
                    if (neighbor.equals(destination)) {
                        return createRoute(visited, destination);
                    }
                }
            }
        }
        return null;
    }

    private List<String> createRoute(Map<String, String> visited, String destination) {
        List<String> route = new ArrayList<>();
        String current = destination;
        while (visited.containsKey(current)) {
            route.add(current);
            current = visited.get(current);
        }
        if (current != null)
            route.add(current);

        Collections.reverse(route);
        return route;
    }
}