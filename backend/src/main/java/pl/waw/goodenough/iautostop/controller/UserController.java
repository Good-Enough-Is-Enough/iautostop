package pl.waw.goodenough.iautostop.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.waw.goodenough.iautostop.model.dto.DriversRouteDto;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.operation.UserOperations;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserOperations userOperations;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserLoggedInDto> getUser(@PathVariable(value = "id") final String id) {

        final UserLoggedInDto userLoggedInDto = userOperations.getUser(id);

        return new ResponseEntity<>(userLoggedInDto, OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@Validated @RequestBody UserLoggedInDto userLoggedInDto) {

        if (!("passenger".equals(userLoggedInDto.getRole()) || "driver".equals(userLoggedInDto.getRole()))) {
            throw new ResponseStatusException(BAD_REQUEST, "Role must be 'driver' or 'passenger'");
        }

        if (userLoggedInDto.getTravelFrom().equals(userLoggedInDto.getTravelTo())) {

            throw new ResponseStatusException(BAD_REQUEST, "You cannot put the same address in from and to fields");
        }

        userOperations.createUser(userLoggedInDto);

        return new ResponseEntity<>(CREATED);
    }

    @GetMapping(value = "/{driverId}/available-passengers")
    public ResponseEntity<List<UserLoggedInDto>> getAvailablePassengers(
            @PathVariable(value = "driverId") final String driverId) {

        final List<UserLoggedInDto> userLoggedInDtoList = userOperations.getAvailablePassengers(driverId);

        return new ResponseEntity<>(userLoggedInDtoList, OK);
    }

    @GetMapping(value = "/{passengerId}/assigned-driver")
    public ResponseEntity<UserLoggedInDto> getAssignedDriver(
            @PathVariable(value = "passengerId") final String passengerId) {

        final UserLoggedInDto assignedDriver = userOperations.getAssignedDriver(passengerId);

        return new ResponseEntity<>(assignedDriver, OK);
    }

    @GetMapping(value = "/{driverId}/streets")
    public ResponseEntity<DriversRouteDto> getStreetsForDriversRoute(
            @PathVariable(value = "driverId") final String driverId) {

        final DriversRouteDto streets = userOperations.getStreetNamesForDriver(driverId);
        return new ResponseEntity<>(streets, OK);
    }

    @DeleteMapping(value = "/driver/{driverId}/end-trip")
    public ResponseEntity endTripForDriver(
            @PathVariable(value = "driverId") final String driverId) {

        userOperations.endTripForDriver(driverId);

        return new ResponseEntity(NO_CONTENT);
    }

    @PostMapping(value = "/{passengerId}/connectTo/{driverId}")
    public ResponseEntity<Object> connectPassengerToDriver(
            @PathVariable(value = "passengerId") final String passengerId,
            @PathVariable(value = "driverId") final String driverId) {

        userOperations.connectPassengerToDriver(passengerId, driverId);

        return new ResponseEntity<>(CREATED);
    }

    @GetMapping(value = "/connectedTo/{driverId}")
    public ResponseEntity<List<UserLoggedInDto>> getPassengersConnectedToDriver(
            @PathVariable(value = "driverId") final String driverId) {

        final List<UserLoggedInDto> connectedPassengersToDriver = userOperations.getPassengersConnectedToDriver(driverId);

        return new ResponseEntity<>(connectedPassengersToDriver, OK);
    }
}