package serviceImpl;

import model.Customer;
import model.Edge;
import model.Node;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import service.OptimalRouteCalculationService;

import java.util.HashSet;
import java.util.Set;

public class OptimalRouteCalculationServiceImpl implements OptimalRouteCalculationService {

    private static OptimalRouteCalculationService optimalRouteCalculationService;

    private OptimalRouteCalculationServiceImpl() {
    }

    public static synchronized OptimalRouteCalculationService getInstance(){
        if(optimalRouteCalculationService==null){
            optimalRouteCalculationService=new OptimalRouteCalculationServiceImpl();
        }
        return optimalRouteCalculationService;
    }

    @Override
    public Pair<Double, String> computeOptimalRoute(Node deliveryAgent) {
        return minDistanceToCoverAll(deliveryAgent, new HashSet<>(), deliveryAgent.getUser().getUserName());
    }

    public static synchronized Pair<Double, String> minDistanceToCoverAll(Node root, Set<String> visited, String path){

        if (root.getUser() instanceof Customer){
            String restaurantId = ((Customer) root.getUser()).getRestaurantId();
            if (visited.contains(restaurantId)){
                visited.add(root.getUser().getUserId());
            }
        }else{
            visited.add(root.getUser().getUserId());
        }

        double min = 0;
        String nextPath = "";

        for (Edge edge: root.getRoutes()){
            if (!visited.contains(edge.getNode().getUser().getUserId())){
                Pair<Double, String> pair = minDistanceToCoverAll(edge.getNode(), visited,
                        edge.getNode().getUser().getUserName());
                if (min==0){
                    min=edge.getTimeTakenToReachNode()+pair.getLeft();
                    nextPath=pair.getRight();
                }else {
                    if (pair.getLeft()+edge.getTimeTakenToReachNode()<min){
                        min=pair.getLeft()+edge.getTimeTakenToReachNode();
                        nextPath=pair.getRight();
                    }
                }
            }
        }

        visited.remove(root.getUser().getUserId());

        return Pair.of(min, StringUtils.isNotEmpty(nextPath)?path+"->"+nextPath:path);

    }
}
