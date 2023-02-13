package com.country.routing.resource;

import com.country.routing.model.RouteResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.country.routing.model.RouteConstants.DESTINATION;
import static com.country.routing.model.RouteConstants.ORIGIN;

@RequestMapping(value = "/")
public interface EndPointResource {

    @GetMapping("routing/{" + ORIGIN + "}/{" + DESTINATION + "}")
    ResponseEntity<RouteResponse> findRoute(@PathVariable(ORIGIN) String origin, @PathVariable(DESTINATION) String destination);
}
