package pl.waw.goodenough.iautostop.repository;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HereRouteRepository implements RouteApiRepository {

    @Value("${here.api.app.id}")
    private String APP_ID;
    @Value("${here.api.app.code}")
    private String APP_CODE;
    private static final String MODE = "fastest;car;traffic:disabled";
    private static final String URL = "https://route.api.here.com/routing/7.2/calculateroute.json";
    private static final String MANOUVER_ATTRIBUTES = "roadName";

    private RestTemplate restTemplate;

    public HereRouteRepository(RestTemplate rt) {
        restTemplate = rt;
    }

    public List<String> getRouteStreetNames(CoordinatesDto travelFrom, CoordinatesDto travelTo) {

        String url = URL + "?" + "app_id=" + APP_ID + "&app_code=" + APP_CODE + "&waypoint0=" + travelFrom + "&waypoint1=" +
                travelTo + "&mode=" + MODE + "&maneuverAttributes=" + MANOUVER_ATTRIBUTES;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String jsonPathForRoute = "$['response']['route'][0]['leg'][0]['maneuver'][*]['roadName']";
        String jsonPathForStartPoint = "$['response']['route'][0]['waypoint'][0]['mappedRoadName']";
        String jsonPathForEndPoint = "$['response']['route'][0]['waypoint'][1]['mappedRoadName']";
        String route = response.getBody();
        DocumentContext responseJsonCotext = JsonPath
                .parse(route);
        List<String> streets = new ArrayList<>();
        streets.add(responseJsonCotext.read(jsonPathForStartPoint));
        streets.addAll(responseJsonCotext.read(jsonPathForRoute));
        streets.add(responseJsonCotext.read(jsonPathForEndPoint));

        Set<String> noDuplicateStreets = new LinkedHashSet<>(streets);

        return noDuplicateStreets
                .stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}
