package pl.waw.goodenough.iautostop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;
import pl.waw.goodenough.iautostop.repository.MapApiRepository;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    MapApiRepository mapApiRepository;

    @GetMapping("/test")
    public List<String> dupa(String from, String to) {
        return mapApiRepository.getRouteStreetNames(CoordinatesDto.parseString(from), CoordinatesDto.parseString(to));
    }

}
