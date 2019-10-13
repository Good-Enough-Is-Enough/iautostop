package pl.waw.goodenough.iautostop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;
import pl.waw.goodenough.iautostop.repository.RouteApiRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteService {

    RouteApiRepository routeApiRepository;

    public List<String> getRouteStreetNames(CoordinatesDto travelFrom, CoordinatesDto travelTo) {
        return routeApiRepository.getRouteStreetNames(travelFrom, travelTo);
    }
}
