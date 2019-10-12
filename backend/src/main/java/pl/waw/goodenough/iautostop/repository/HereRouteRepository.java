package pl.waw.goodenough.iautostop.repository;

import com.jayway.jsonpath.JsonPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HereRouteRepository implements RouteRepository {

    private static final String APP_ID = "i8w3y91fhZ5nDPijP7XL";
    private static final String APP_CODE = "v6YKXNdMEl2eGoTGHyToSQ";
    private static final String MODE = "fastest;car;traffic:disabled";
    public static final String URL = "https://route.api.here.com/routing/7.2/calculateroute.json";

    private RestTemplate restTemplate;

    public HereRouteRepository(RestTemplate rt) {
        restTemplate = rt;
    }

    public List<String> getRouteStreetNames(String travelFrom, String travelTo) {
        Map<String, String > params = new HashMap<>();
        params.put("app_id", APP_ID);
        params.put("app_code", APP_CODE);
        params.put("waypoint0", travelFrom);
        params.put("waypoint1", travelTo);
        params.put("mode", MODE);

        String url = URL + "?" + "app_id=" + APP_ID + "&app_code=" + APP_CODE + "&waypoint0=" + travelFrom + "&waypoint1=" +
                travelTo + "&mode=" + MODE;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String jsonPath = "$['response']['route'][0]['waypoint'][*]['mappedRoadName']";
        String route = response.getBody();
        return JsonPath.parse(route).read(jsonPath);
    }
}
