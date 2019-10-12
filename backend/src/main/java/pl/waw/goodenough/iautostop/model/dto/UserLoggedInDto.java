package pl.waw.goodenough.iautostop.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoggedInDto {

    @NotNull(message = "Id cannot be null")
    private String id;
    @NotNull(message = "Role must be 'passenger' or 'driver'")
    @Pattern.List({
            @Pattern(regexp = "driver", message = "Role must be passenger or driver"),
            @Pattern(regexp = "passenger", message = "Role must be passenger or driver")
    })
    private String role;
    @NotNull(message = "travelFrom cannot be null")
    private String travelFrom;
    @NotNull(message = "travelTo cannot be null")
    private String travelTo;
}