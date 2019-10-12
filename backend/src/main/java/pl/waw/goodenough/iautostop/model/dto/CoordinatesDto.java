package pl.waw.goodenough.iautostop.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesDto {

    private Double latitude;
    private Double longitude;

    public static CoordinatesDto parseString(String stringCoordinates) {

        String[] coordinates = stringCoordinates.split(",");

        return CoordinatesDto.builder()
                .latitude(Double.parseDouble(coordinates[0]))
                .longitude(Double.parseDouble(coordinates[1]))
                .build();
    }

    public String toString() {
      return longitude.toString() + "," + latitude.toString();
    }
}
