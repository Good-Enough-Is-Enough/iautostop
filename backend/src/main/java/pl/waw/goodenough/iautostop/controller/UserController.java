package pl.waw.goodenough.iautostop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.operation.UserOperations;

@RestController
public class UserController {

    private UserOperations userOperations;

    @GetMapping("")
    public ResponseEntity<UserLoggedInDto> getUser(
            @RequestParam(value = "id") final String id) {

        final UserLoggedInDto userLoggedInDto = userOperations.getUser(id);

        return new ResponseEntity<>(userLoggedInDto, HttpStatus.OK);
    }
}
