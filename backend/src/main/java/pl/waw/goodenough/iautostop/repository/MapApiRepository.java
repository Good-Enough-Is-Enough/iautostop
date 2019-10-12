package pl.waw.goodenough.iautostop.repository;

import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;

import java.util.List;

public interface MapApiRepository {

    List<String> getRouteStreetNames(CoordinatesDto travelFrom, CoordinatesDto travelTo);
    CoordinatesDto getCoordinatesByLocationId(String locationId);
}
