package pl.waw.goodenough.iautostop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.operation.UserOperations;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserOperations userOperations;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserLoggedInDto> getUser(
            @PathVariable(value = "id") final String id) {

        final UserLoggedInDto userLoggedInDto = userOperations.getUser(id);

        return new ResponseEntity<>(userLoggedInDto, OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(
            @RequestBody UserLoggedInDto userLoggedInDto) {

        if ("passenger".equals(userLoggedInDto.getRole())) {
            userOperations.createUser(userLoggedInDto);
        }

        return new ResponseEntity<>(OK);
    }
}