package pl.waw.goodenough.iautostop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.waw.goodenough.iautostop.repository.RouteRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteService {

    RouteRepository routeRepository;

    public List<String> getRouteStreetNames(String travelFrom, String travelTo) {
        return routeRepository.getRouteStreetNames(travelFrom, travelTo);
    }
}
