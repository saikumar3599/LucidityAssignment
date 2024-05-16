package service;

import model.DeliveryAgent;
import model.Node;
import model.User;

import java.util.Map;

public interface LocationMappingService {

    Map<String, Node> createLocationMapping(DeliveryAgent deliveryAgent, Map<String, User> userIdMap);
}
