package com.country.routing;

import com.country.routing.controller.EndPointController;
import com.country.routing.model.RouteResponse;
import com.country.routing.service.EndPointService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EndPointControllerTest {

    @InjectMocks
    private EndPointController endPointController;

    @Mock
    private EndPointService endPointService;

    @Test
    public void testFindRoute_Success() {
        when(endPointService.calculateRoute("origin", "destination")).thenReturn(Arrays.asList("origin", "destination"));
        ResponseEntity<RouteResponse> response = endPointController.findRoute("origin", "destination");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getBody().getCode());
        assertEquals(Arrays.asList("origin", "destination"), response.getBody().getRoute());
    }

    @Test
    public void testFindRoute_BadRequest() {
        when(endPointService.calculateRoute("origin", "destination")).thenReturn(null);
        ResponseEntity<RouteResponse> response = endPointController.findRoute("origin", "destination");
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(400, response.getBody().getCode());
        assertEquals(null, response.getBody().getRoute());
    }
}