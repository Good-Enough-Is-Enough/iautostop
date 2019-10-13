package pl.waw.goodenough.iautostop.repository;

import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;

public interface MapApiRepository {

    CoordinatesDto getCoordinatesByLocationId(String locationId);
}
