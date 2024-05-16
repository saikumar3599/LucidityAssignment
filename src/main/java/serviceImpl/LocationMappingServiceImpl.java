package serviceImpl;

import model.*;
import service.LocationMappingService;
import util.CalculationUtility;

import java.util.*;

public class LocationMappingServiceImpl implements LocationMappingService {

    private static LocationMappingService locationMappingService;

    private LocationMappingServiceImpl(){

    }

    public static synchronized LocationMappingService getInstance(){
        if (locationMappingService==null){
            locationMappingService=new LocationMappingServiceImpl();
        }
        return locationMappingService;
    }

    @Override
    public Map<String, Node> createLocationMapping(DeliveryAgent deliveryAgent, Map<String, User> userIdMap) {

        List<User> restaurants = new ArrayList<>();
        List<User> customers = new ArrayList<>();

        for (OrderInfo orderInfo:
                deliveryAgent.getOrderInfoList()) {
            restaurants.add(userIdMap.get(orderInfo.getRestaurantId()));
            customers.add(userIdMap.get(orderInfo.getCustomerId()));
        }

        Map<String, Node> map = new HashMap<>();

        mapDeliveryAgentToRestaurants(restaurants, deliveryAgent, map);

        mapRestaurantsAndCustomersToEachOther(restaurants, map);

        mapRestaurantsAndCustomersToEachOther(customers, map);

        mapRestaurantsToCustomersAndReverse(restaurants, customers, map);

        mapRestaurantsToCustomersAndReverse(customers, restaurants, map);

        return map;
    }

    private void mapRestaurantsToCustomersAndReverse(List<User> restaurants, List<User> customers, Map<String, Node> map){
        for (int i=0;i<restaurants.size();i++){
            Node parent = map.get(restaurants.get(i).getUserId());
            List<Edge> edges = new ArrayList<>();
            for (int j=0;j<customers.size();j++){
                Node child = map.get(customers.get(j).getUserId());
                double timeToReachRestaurant = CalculationUtility
                        .calculateTimeTakenFromOriginToDestination(parent.getUser().getGeoLocationData(),
                                child.getUser().getGeoLocationData(), 20.0);
                Edge edge = Edge.builder()
                        .timeTakenToReachNode(timeToReachRestaurant)
                        .node(child)
                        .build();

                edges.add(edge);
            }
            parent.getRoutes().addAll(edges);
        }
    }

    private void mapRestaurantsAndCustomersToEachOther(List<User> users, Map<String, Node> map){
        for (int i=0;i<users.size();i++){
            Node parent = map.getOrDefault(users.get(i).getUserId(), Node.builder().user(users.get(i)).build());
            map.put(users.get(i).getUserId(), parent);
            List<Edge> edges = new ArrayList<>();
            for (int j=0;j<users.size();j++){
                if (j==i){
                    continue;
                }
                Node child = map.getOrDefault(users.get(j).getUserId(), Node.builder().user(users.get(j)).build());
                map.put(users.get(j).getUserId(), child);
                double timeToReachRestaurant = CalculationUtility
                        .calculateTimeTakenFromOriginToDestination(parent.getUser().getGeoLocationData(),
                                child.getUser().getGeoLocationData(), 20.0);
                Edge edge = Edge.builder()
                        .timeTakenToReachNode(timeToReachRestaurant)
                        .node(child)
                        .build();

                edges.add(edge);
            }
            parent.setRoutes(edges);
        }
    }

    private void mapDeliveryAgentToRestaurants(List<User> restaurants,
      DeliveryAgent deliveryAgent, Map<String, Node> map){
        List<Edge> edges = new ArrayList<>();

        for (User user : restaurants) {
            double timeToReachRestaurant = CalculationUtility
                    .calculateTimeTakenFromOriginToDestination(deliveryAgent.getGeoLocationData(),
                            user.getGeoLocationData(), 20.0);
            Node node = Node.builder()
                    .user(user)
                    .build();
            Edge edge = Edge.builder()
                    .node(node)
                    .timeTakenToReachNode(timeToReachRestaurant)
                    .build();
            edges.add(edge);
            map.put(user.getUserId(), node);
        }

        Node deliveryAgentNode = Node.builder()
                .user(deliveryAgent)
                .build();
        map.put(deliveryAgent.getUserId(), deliveryAgentNode);
        deliveryAgentNode.setRoutes(edges);
    }
}
