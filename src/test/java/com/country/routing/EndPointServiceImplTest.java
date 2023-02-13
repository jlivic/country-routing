package com.country.routing;

import com.country.routing.service.EndPointServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class EndPointServiceImplTest {

    private final EndPointServiceImpl endPointService = new EndPointServiceImpl();

    @Test
    public void testCalculateRoute_Success() {
        List<String> route = endPointService.calculateRoute("CZE", "FRA");
        String[] expected = {"CZE", "DEU", "FRA"};
        Assertions.assertEquals(expected.length, route.toArray().length);
    }

    @Test
    public void testCalculateRoute_NoRouteExists() {
        List<String> route = endPointService.calculateRoute("CZE", "CRO");
        Assertions.assertNull(route);
    }

}