package pl.waw.goodenough.iautostop.repository;

import pl.waw.goodenough.iautostop.model.dto.Coordinates;

import java.util.List;

public interface RouteRepository {

    List<String> getRouteStreetNames(String travelFrom, String travelTo);
    Coordinates getCoordinatesByLocationId(String locationId);
}
