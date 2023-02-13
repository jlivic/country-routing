package com.country.routing.service;

import java.util.List;

public interface EndPointService {
    List<String> calculateRoute(String origin, String destination);

}
