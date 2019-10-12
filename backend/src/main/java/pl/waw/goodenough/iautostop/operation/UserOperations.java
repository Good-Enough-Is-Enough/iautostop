package pl.waw.goodenough.iautostop.operation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.model.entity.AppUser;
import pl.waw.goodenough.iautostop.model.entity.AppUserRoute;
import pl.waw.goodenough.iautostop.repository.AppUserRepository;
import pl.waw.goodenough.iautostop.repository.AppUserRouteRepository;
import pl.waw.goodenough.iautostop.service.RouteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserOperations {

    private AppUserRepository appUserRepository;

    private AppUserRouteRepository appUserRouteRepository;

    private RouteService routeService;

    public UserLoggedInDto getUser(final String id) {

        Optional<AppUserRoute> userRoute = appUserRouteRepository.findByUserId(id);
        Optional<AppUser> user = appUserRepository.findByUserId(id);

        if (userRoute.isPresent() && user.isPresent()) {

            return UserLoggedInDto
                    .builder()
                    .id(userRoute.get().getUserId())
                    .role(user.get().getRole())
                    .travelFrom(userRoute.get().getTravelFrom())
                    .travelTo(userRoute.get().getTravelTo())
                    .build();
        }

        return null;
    }

    @Transactional
    public void createUser(final UserLoggedInDto userLoggedInDto) {

        AppUser appUser = new AppUser();
        appUser.setId(userLoggedInDto.getId());
        appUser.setRole(userLoggedInDto.getRole());

        appUserRepository.save(appUser);

        AppUserRoute appUserRoute = new AppUserRoute();
        appUserRoute.setUserId(userLoggedInDto.getId());
        appUserRoute.setTravelFrom(userLoggedInDto.getTravelFrom());

        if ("driver".equals(userLoggedInDto.getRole())) {

            CoordinatesDto coordinatesDtoFrom = routeService.getCoordinatesByLocationId(userLoggedInDto.getTravelFrom());
            CoordinatesDto coordinatesDtoTo = routeService.getCoordinatesByLocationId(userLoggedInDto.getTravelTo());
            List<String> routeStreetNamesList = routeService.getRouteStreetNames(coordinatesDtoFrom, coordinatesDtoTo);

            appUserRoute.setTravelStreetList(routeStreetNamesList.toString());
        }

        appUserRoute.setTravelTo(userLoggedInDto.getTravelTo());
        appUserRouteRepository.save(appUserRoute);
    }

    public List<UserLoggedInDto> getAvailablePassengers(String driverId) {

        final AppUserRoute driver = appUserRouteRepository.getDriverById(driverId);
        final List<AppUserRoute> passengers = appUserRouteRepository.findAllPassengers();

        List<UserLoggedInDto> matchedPassengers = new ArrayList<>();

        final List<String> driverTravelList = getStreetNamesList(driver);

        for (AppUserRoute appUserRoute : passengers) {

            RouteMatcher routeMatcher = new RouteMatcher(driverTravelList, appUserRoute.getTravelFrom(), appUserRoute.getTravelTo());

            if (routeMatcher.match()) {

                matchedPassengers.add(
                        UserLoggedInDto
                                .builder()
                                .id(appUserRoute.getUserId())
                                .travelFrom(appUserRoute.getTravelFrom())
                                .travelTo(appUserRoute.getTravelTo())
                                .role("passenger")
                                .build()
                );
            }
        }

        return matchedPassengers;
    }

    public List<String> getStreetNamesForDriver(String driverId) {
        AppUserRoute driver = appUserRouteRepository.getDriverById(driverId);
        return getStreetNamesList(driver);
    }

    private List<String> getStreetNamesList(AppUserRoute driver) {
        return Arrays.asList(driver.getTravelStreetList().split(","));
    }

}
