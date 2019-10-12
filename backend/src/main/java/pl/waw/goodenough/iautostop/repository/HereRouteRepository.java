package pl.waw.goodenough.iautostop.repository;

import com.jayway.jsonpath.JsonPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HereRouteRepository implements RouteRepository {

    private static final String APP_ID = "i8w3y91fhZ5nDPijP7XL";
    private static final String APP_CODE = "v6YKXNdMEl2eGoTGHyToSQ";
    private static final String MODE = "fastest;car;traffic:disabled";
    private static final String URL = "https://route.api.here.com/routing/7.2/calculateroute.json";
    private static final String MANOUVER_ATTRIBUTES = "roadName";

    private RestTemplate restTemplate;

    public HereRouteRepository(RestTemplate rt) {
        restTemplate = rt;
    }

    public List<String> getRouteStreetNames(String travelFrom, String travelTo) {

        String url = URL + "?" + "app_id=" + APP_ID + "&app_code=" + APP_CODE + "&waypoint0=" + travelFrom + "&waypoint1=" +
                travelTo + "&mode=" + MODE +"&maneuverAttributes=" + MANOUVER_ATTRIBUTES;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String jsonPath = "$['response']['route'][0]['leg'][0]['maneuver'][*]['roadName']";
        String route = response.getBody();
        List<String> streets = JsonPath
                .parse(route)
                .read(jsonPath);

        Set<String> noDuplicateStreets= new HashSet<>(streets);

        return noDuplicateStreets
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
