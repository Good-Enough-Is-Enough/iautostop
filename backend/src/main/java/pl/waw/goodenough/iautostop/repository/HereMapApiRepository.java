package pl.waw.goodenough.iautostop.repository;

import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;

import java.util.HashMap;

public class HereMapApiRepository implements MapApiRepository {

    @Value("${here.api.app.id}")
    private String APP_ID;
    @Value("${here.api.app.code}")
    private String APP_CODE;
    private static final String GEOCODING_API = "https://geocoder.api.here.com/6.2/geocode.json";

    private RestTemplate restTemplate;

    public HereMapApiRepository(RestTemplate rt) {
        restTemplate = rt;
    }

    @Override
    public CoordinatesDto getCoordinatesByLocationId(String locationId) {

        String url = GEOCODING_API + "?" + "app_id=" + APP_ID + "&app_code=" + APP_CODE + "&locationid=" + locationId;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String jsonPath = "$['Response']['View'][0]['Result'][0]['Location']['DisplayPosition']";
        String responseBody = response.getBody();

        HashMap<String, Double> coordinates = JsonPath.parse(responseBody).read(jsonPath);

        return CoordinatesDto.builder()
                .latitude(coordinates.get("Latitude"))
                .longitude(coordinates.get("Longitude")).build();
    }
}
