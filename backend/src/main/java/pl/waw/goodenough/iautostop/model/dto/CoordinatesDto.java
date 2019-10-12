package pl.waw.goodenough.iautostop.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoordinatesDto {

    private Double latitude;
    private Double longitude;

    public String toString() {
      return longitude.toString() + "," + latitude.toString();
    }
}
