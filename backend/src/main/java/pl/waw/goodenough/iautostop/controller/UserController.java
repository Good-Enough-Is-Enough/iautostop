package pl.waw.goodenough.iautostop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;

@RestController
public class UserController {

    @GetMapping("")
    public ResponseEntity<UserLoggedInDto> getUser(
            @RequestParam(value="id") final String name) {

        return new ResponseEntity<UserLoggedInDto>();
    }
}
