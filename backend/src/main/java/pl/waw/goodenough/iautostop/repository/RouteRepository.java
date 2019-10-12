package pl.waw.goodenough.iautostop.repository;

import pl.waw.goodenough.iautostop.model.dto.CoordinatesDTO;

import java.util.List;

public interface RouteRepository {

    List<String> getRouteStreetNames(String travelFrom, String travelTo);
    CoordinatesDTO getCoordinatesByLocationId(String locationId);
}
