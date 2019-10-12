package pl.waw.goodenough.iautostop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;
import pl.waw.goodenough.iautostop.repository.RouteApiRepository;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    RouteApiRepository routeApiRepository;

    @GetMapping("/test")
    public List<String> dupa(String from, String to) {
        return routeApiRepository.getRouteStreetNames(CoordinatesDto.parseString(from), CoordinatesDto.parseString(to));
    }

}
