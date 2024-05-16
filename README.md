## Overview of the approach
In this project, there are 3 types of users, Customer, Restaurant and DeliveryAgent. We will start find our optimalPath until all the deliveries assigned to the delivery agent is completed. For the skae of simplicity, we are assuming that the graph initialized does not contain any ecxtra restaurants or customers other than the ones related to the orders.

We are using haversine formula for distance calculation and using the information mentioned in the problem statememt that the speed is 20 km/hr. We are calculating the time and storing it while constructing out weighted undirected graph.

After this graph is constructed, we perform the DFS starting from our delivery agent and check the optimal path . Not that dueing this DFS we will reVisit the customerNodes if we reach a particular customer without their respective order.

## Key Components
`User` -> This class is used to put the user attributes together, such as userName, userId and their geoLocation.

`DeliveryAgent` -> This class extends User , and will have `orderInfo` as it's field which will have the info of the orders that the deliveryAgent has to execute.

`Restaurant` -> This class extends User. This is used to represent the restaurant which will fulfill the order.

`Customer` -> This class extends User. This class will have `restaurantId` as extra field indicating the restaurantId from which the customer has ordered.

`Node` -> This class will be used while creating the graph. This will contain the User object and will have list of Edges.

`Edge` -> This class will be used to represent the edges that are originating from a node. This contains, the time taken to travel between nodes, and the destination node.

`LocationMappingService` -> This class is responsible for creating the graph using the deliveryAgent object and the list of all the objects involved here

`OptimalRouteCalculationService` -> This class is responsible for computing the minimum time required to execute all orders and the path that should be taken by the agent to complete in that minimum time.

`CalculationUtility` -> This is used to calculate the time taken to travel from one GeoLocation to other GeoLocation.
