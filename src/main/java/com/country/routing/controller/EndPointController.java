package com.country.routing.controller;

import com.country.routing.model.RouteResponse;
import com.country.routing.resource.EndPointResource;
import com.country.routing.service.EndPointService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EndPointController implements EndPointResource {

    private EndPointService service;

    public EndPointController(EndPointService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<RouteResponse> findRoute(String origin, String destination) {
        List<String> route = service.calculateRoute(origin, destination);
        if (route == null) {
            return ResponseEntity.badRequest().body(new RouteResponse(HttpStatus.BAD_REQUEST.value(), null));
        }
        return ResponseEntity.ok(new RouteResponse(HttpStatus.OK.value(), route));
    }
}
