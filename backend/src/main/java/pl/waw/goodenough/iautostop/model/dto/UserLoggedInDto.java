package pl.waw.goodenough.iautostop.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoggedInDto {

    private String id;
    private String role;
    private String travelFrom;
    private String travelTo;
}