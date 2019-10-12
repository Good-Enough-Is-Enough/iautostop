package pl.waw.goodenough.iautostop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.operation.UserOperations;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserOperations userOperations;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserLoggedInDto> getUser(
            @PathVariable(value = "id") final String id) {

        final UserLoggedInDto userLoggedInDto = userOperations.getUser(id);

        return new ResponseEntity<>(userLoggedInDto, HttpStatus.OK);
    }
}