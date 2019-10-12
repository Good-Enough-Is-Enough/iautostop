package pl.waw.goodenough.iautostop.repository;

import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;

import java.util.List;

public interface RouteRepository {

    List<String> getRouteStreetNames(CoordinatesDto travelFrom, CoordinatesDto travelTo);
    CoordinatesDto getCoordinatesByLocationId(String locationId);
}
