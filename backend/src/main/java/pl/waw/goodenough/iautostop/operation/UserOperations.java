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

            AppUser u = user.get();
            AppUserRoute ur = userRoute.get();

            return UserLoggedInDto
                    .builder()
                    .id(ur.getUserId())
                    .role(u.getRole())
                    .travelFrom(ur.getTravelFrom())
                    .travelTo(ur.getTravelTo())
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
            List<String> routeStreetNamesList =
                    routeService.getRouteStreetNames(CoordinatesDto.parseString(userLoggedInDto.getTravelFrom()), CoordinatesDto.parseString(userLoggedInDto.getTravelTo()));
            appUserRoute.setTravelStreetList(routeStreetNamesList.toString());
        }

        appUserRoute.setTravelTo(userLoggedInDto.getTravelTo());
        appUserRouteRepository.save(appUserRoute);
    }

    public List<UserLoggedInDto> getAvailablePassengers(String driverId) {

        Optional<AppUserRoute> driver = appUserRouteRepository.findByUserId(driverId);
        List<AppUserRoute> passengers = appUserRouteRepository.findAllPassengers();

        List<UserLoggedInDto> matchedPassengers = new ArrayList<>();

        if (driver.isPresent()) {

            AppUserRoute d = driver.get();

            List<String> driverTravelList = Arrays.asList(d.getTravelStreetList().split(","));

            for (AppUserRoute appUserRoute : passengers) {

                RouteMatcher routeMatcher = new RouteMatcher(driverTravelList, appUserRoute.getTravelFrom(), appUserRoute.getTravelTo());

                if (routeMatcher.match()) {

                    matchedPassengers.add(
                            UserLoggedInDto
                                    .builder()
                                    .id(appUserRoute.getUserId())
                                    .travelFrom(appUserRoute.getTravelFrom())
                                    .travelTo(appUserRoute.getTravelTo())
                                    .build()
                    );
                }
            }
        }

        return matchedPassengers;
    }
}
