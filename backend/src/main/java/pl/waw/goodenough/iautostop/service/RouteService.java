package pl.waw.goodenough.iautostop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;
import pl.waw.goodenough.iautostop.repository.MapApiRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteService {

    MapApiRepository mapApiRepository;

    public List<String> getRouteStreetNames(CoordinatesDto travelFrom, CoordinatesDto travelTo) {
        return mapApiRepository.getRouteStreetNames(travelFrom, travelTo);
    }

    public CoordinatesDto getCoordinatesByLocationId(String locationId) {
        return mapApiRepository.getCoordinatesByLocationId(locationId);
    }
}
