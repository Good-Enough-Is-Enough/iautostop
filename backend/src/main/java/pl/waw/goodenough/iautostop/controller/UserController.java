package pl.waw.goodenough.iautostop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.operation.UserOperations;

import java.util.List;

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

        userOperations.createUser(userLoggedInDto);

        return new ResponseEntity<>(OK);
    }

    @GetMapping(value = "/{driverId}/available-passengers")
    public ResponseEntity<List<UserLoggedInDto>> getAvailablePassengers(
            @PathVariable(value = "driverId") final String driverId) {

        List<UserLoggedInDto> userLoggedInDtoList = userOperations.getAvailablePassengers(driverId);

        return new ResponseEntity<>(userLoggedInDtoList, OK);
    }
}