package pl.waw.goodenough.iautostop.operation;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RouteMatcher {

    List<String> driversRoute;
    String passengerStart;
    String passengerEnd;

    public boolean match(){
        int matchingStartPoint = pointWithinRoute(driversRoute, passengerStart);
        if (matchingStartPoint != -1) {
            List<String> remainingRoute = driversRoute.subList(matchingStartPoint, driversRoute.size());
            int matchingEndPoint = pointWithinRoute(remainingRoute, passengerEnd);
            return matchingEndPoint != -1;
        } else {
            return false;
        }
    }

    private int pointWithinRoute(List<String> route, String point) {
        for (int i = 0; i < route.size(); i++) {
            if (route.get(i).equals(point)) {
                return i;
            }
        }
        return -1;
    }
}
