package pl.waw.goodenough.iautostop.repository;

import java.util.List;

public interface RouteRepository {

    List<String> getRouteStreetNames(String travelFrom, String travelTo);

}
