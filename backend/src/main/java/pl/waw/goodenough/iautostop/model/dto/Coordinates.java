package pl.waw.goodenough.iautostop.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinates {
  private Double latitude;
  private Double longitude;
}
