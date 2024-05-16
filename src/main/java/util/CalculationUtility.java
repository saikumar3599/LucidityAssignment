package util;

import model.GeoLocationData;

public class CalculationUtility {

    public static double calculateTimeTakenFromOriginToDestination(GeoLocationData origin, GeoLocationData destination,
      double speed){

        double latDistance = Math.toRadians(destination.getLatitude() - origin.getLatitude());
        double longDistance = Math.toRadians(destination.getLongitude() - origin.getLongitude());

        double originLatInRadians = Math.toRadians(origin.getLatitude());
        double destinationLatInRadians = Math.toRadians(destination.getLatitude());

        double a = Math.pow(Math.sin(latDistance / 2), 2) +
                Math.pow(Math.sin(longDistance / 2), 2) *
                        Math.cos(originLatInRadians) *
                        Math.cos(destinationLatInRadians);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        double distance = rad * c;
        return distance/speed;
    }
}
