package org.example;

import model.*;
import org.apache.commons.lang3.tuple.Pair;
import service.LocationMappingService;
import service.OptimalRouteCalculationService;
import serviceImpl.LocationMappingServiceImpl;
import serviceImpl.OptimalRouteCalculationServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static LocationMappingService locationMappingService = LocationMappingServiceImpl.getInstance();
    private static OptimalRouteCalculationService optimalRouteCalculationService =
            OptimalRouteCalculationServiceImpl.getInstance();

    public static void main(String[] args) {
        OrderInfo orderInfo1 = OrderInfo.builder()
                .restaurantId("R1")
                .customerId("C1").build();
        OrderInfo orderInfo2 = OrderInfo.builder()
                .restaurantId("R2")
                .customerId("C2").build();
        DeliveryAgent deliveryAgent = new DeliveryAgent();
        deliveryAgent.setOrderInfoList(Arrays.asList(orderInfo1, orderInfo2));

        deliveryAgent.setGeoLocationData(GeoLocationData.builder().latitude(40.7128).longitude(-74.0060).build());
        deliveryAgent.setUserId("Aman");
        deliveryAgent.setUserName("Aman");

        Restaurant restaurant1 = Restaurant.builder().build();
        restaurant1.setGeoLocationData(GeoLocationData.builder().latitude(40.7840).longitude(-74.0060).build());
        restaurant1.setUserId("R1");
        restaurant1.setUserName("R1");

        Restaurant restaurant2 = Restaurant.builder().build();
        restaurant2.setGeoLocationData(GeoLocationData.builder().latitude(40.6666).longitude(-74.0101).build());
        restaurant2.setUserId("R2");
        restaurant2.setUserName("R2");

        Customer customer1 = Customer.builder().build();
        customer1.setGeoLocationData(GeoLocationData.builder().latitude(40.6677).longitude(-74.0151).build());
        customer1.setUserId("C1");
        customer1.setUserName("C1");
        customer1.setRestaurantId("R1");

        Customer customer2 = Customer.builder().build();
        customer2.setGeoLocationData(GeoLocationData.builder().latitude(40.6688).longitude(-74.0251).build());
        customer2.setUserId("C2");
        customer2.setUserName("C2");
        customer2.setRestaurantId("R2");

        Map<String, User> map = new HashMap<>();
        map.put(deliveryAgent.getUserId(), deliveryAgent);
        map.put(restaurant1.getUserId(), restaurant1);
        map.put(restaurant2.getUserId(), restaurant2);
        map.put(customer1.getUserId(), customer1);
        map.put(customer2.getUserId(), customer2);

        Map<String, Node> locationMapping = locationMappingService.createLocationMapping(deliveryAgent, map);
        Pair<Double, String> minimumTimeWithPath = optimalRouteCalculationService.computeOptimalRoute(locationMapping
                .get(deliveryAgent.getUserId()));
        System.out.println(minimumTimeWithPath);
    }
}