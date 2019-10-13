package pl.waw.goodenough.iautostop.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DriversRouteDto {

    List<String> streetNames;
    String startCoords;
    String endCoords;
}
