package service;

import model.Node;
import org.apache.commons.lang3.tuple.Pair;

public interface OptimalRouteCalculationService {
    /**
     * @param deliveryAgent - Node of the delivery agent
     * @return pair of minimum Time taken to execute the delivery and the path that
     * should be taken by the agent to deliver orders in the calculated minimumTime
     */
    Pair<Double, String> computeOptimalRoute(Node deliveryAgent);
}
