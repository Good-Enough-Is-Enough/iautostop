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
import pl.waw.goodenough.iautostop.repository.MapApiRepository;
import pl.waw.goodenough.iautostop.service.RouteService;

import java.util.*;

@Service
@AllArgsConstructor
public class UserOperations {

    private AppUserRepository appUserRepository;
    private AppUserRouteRepository appUserRouteRepository;
    private RouteService routeService;
    private MapApiRepository mapApiRepository;

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

        CoordinatesDto coordinatesDtoFrom = mapApiRepository.getCoordinatesByLocationId(userLoggedInDto.getTravelFrom());
        CoordinatesDto coordinatesDtoTo = mapApiRepository.getCoordinatesByLocationId(userLoggedInDto.getTravelTo());

        AppUserRoute appUserRoute = new AppUserRoute();
        appUserRoute.setUserId(userLoggedInDto.getId());
        appUserRoute.setTravelFrom(coordinatesDtoFrom.toString());
        appUserRoute.setTravelTo(coordinatesDtoTo.toString());

        if ("driver".equals(userLoggedInDto.getRole())) {

            List<String> routeStreetNamesList = routeService.getRouteStreetNames(coordinatesDtoFrom, coordinatesDtoTo);

            final String routeStreetNames = routeStreetNamesList.toString();
            appUserRoute.setTravelStreetList(routeStreetNames.substring(1, routeStreetNames.length() - 1));
        }

        appUserRouteRepository.save(appUserRoute);
    }

    public List<UserLoggedInDto> getAvailablePassengers(String driverId) {

        final AppUserRoute driver = appUserRouteRepository.getDriverRouteById(driverId);
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

    public List<String> removeFirstStreet(String driverId) {
        AppUserRoute driverRoute = appUserRouteRepository.getDriverRouteById(driverId);
        if (driverRoute.getTravelStreetList().isEmpty()) {
            appUserRouteRepository.delete(driverRoute);
            return Collections.emptyList();
        } else {
            List<String> streets = getStreetNamesList(driverRoute);
            List<String> reducedStreetList = streets.subList(1, streets.size());

            String reducedStreetsListsString = String.join(", ", reducedStreetList);

            driverRoute.setTravelStreetList(reducedStreetsListsString);
            appUserRouteRepository.save(driverRoute);
            return reducedStreetList;
        }
    }

    public List<String> getStreetNamesForDriver(String driverId) {
        AppUserRoute driver = appUserRouteRepository.getDriverRouteById(driverId);
        return getStreetNamesList(driver);
    }

    private List<String> getStreetNamesList(AppUserRoute appuserRoute) {
        return Arrays.asList(appuserRoute.getTravelStreetList().split(","));
    }
}
