package service;

import model.DeliveryAgent;
import model.Node;
import model.User;

import java.util.Map;

public interface LocationMappingService {

    /**
     * @param deliveryAgent : The User object of the deliveryAgent
     * @param userIdMap : UserId to UserObject map
     * @return the userId to the nodes map. Using one of the nodes we can traverse the whole graph.
     */
    Map<String, Node> createLocationMapping(DeliveryAgent deliveryAgent, Map<String, User> userIdMap);
}
