package pl.waw.goodenough.iautostop.operation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.waw.goodenough.iautostop.model.dto.CoordinatesDto;
import pl.waw.goodenough.iautostop.model.dto.DriversRouteDto;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.model.entity.AppMatchedPairs;
import pl.waw.goodenough.iautostop.model.entity.AppUser;
import pl.waw.goodenough.iautostop.model.entity.AppUserRoute;
import pl.waw.goodenough.iautostop.repository.AppMatchedPairsRepository;
import pl.waw.goodenough.iautostop.repository.AppUserRepository;
import pl.waw.goodenough.iautostop.repository.AppUserRouteRepository;
import pl.waw.goodenough.iautostop.repository.MapApiRepository;
import pl.waw.goodenough.iautostop.service.RouteService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserOperations {

    private AppUserRepository appUserRepository;
    private AppUserRouteRepository appUserRouteRepository;
    private RouteService routeService;
    private MapApiRepository mapApiRepository;
    private AppMatchedPairsRepository appMatchedPairsRepository;

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
                    .name(user.get().getName())
                    .phone(user.get().getPhone())
                    .build();
        }

        return null;
    }

    @Transactional
    public void createUser(final UserLoggedInDto userLoggedInDto) {

        AppUser appUser = new AppUser();
        appUser.setId(userLoggedInDto.getId());
        appUser.setRole(userLoggedInDto.getRole());
        appUser.setName(userLoggedInDto.getName());
        appUser.setPhone(userLoggedInDto.getPhone());
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
        } else {
            List<String> routeStreetNamesList = routeService.getRouteStreetNames(coordinatesDtoFrom, coordinatesDtoTo);
            String start = routeStreetNamesList.get(0);
            String end = routeStreetNamesList.get(routeStreetNamesList.size() -1);
            List<String> startEndNames = Arrays.asList(start, end);

            appUserRoute.setTravelStreetList(String.join(", ", startEndNames));
        }

        appUserRouteRepository.save(appUserRoute);
    }

    public List<UserLoggedInDto> getAvailablePassengers(String driverId) {

        final AppUserRoute driver = appUserRouteRepository.getDriverById(driverId);
        if (driver == null) {
            return Collections.emptyList();
        }

        final List<AppUserRoute> passengers = appUserRouteRepository.findAllAvailablePassengers();

        List<UserLoggedInDto> matchedPassengers = new ArrayList<>();

        final List<String> driverTravelList = getStreetNamesList(driver);

        for (AppUserRoute appUserRoute : passengers) {

            List<String> streets = getStreetNamesList(appUserRoute);
            String start = streets.get(0);
            String end = streets.get(1);

            RouteMatcher routeMatcher = new RouteMatcher(driverTravelList, start, end);

            if (routeMatcher.match()) {

                Optional<AppUser> appUser = appUserRepository.findByUserId(appUserRoute.getUserId());
                String userName = "";
                String userPhone = "";

                if (appUser.isPresent()) {
                    userName = appUser.get().getName();
                    userPhone = appUser.get().getPhone();
                }

                matchedPassengers.add(
                        UserLoggedInDto
                                .builder()
                                .id(appUserRoute.getUserId())
                                .travelFrom(appUserRoute.getTravelFrom())
                                .travelTo(appUserRoute.getTravelTo())
                                .role("passenger")
                                .name(userName)
                                .phone(userPhone)
                                .build()
                );
            }
        }

        return matchedPassengers;
    }

    @Transactional
    public void endTripForDriver(String driverId) {

        removePassengersPairedWithDriver(driverId);

        appUserRouteRepository.deleteById(driverId);
        appUserRepository.deleteById(driverId);
        appMatchedPairsRepository.deleteById(driverId);
    }

    private void removePassengersPairedWithDriver(String driverId) {
        List<AppMatchedPairs> matchedPairs = appMatchedPairsRepository.selectAllByDriverId(driverId);
        List<String> passengersId = matchedPairs
                .stream()
                .map(AppMatchedPairs::getPassengerId)
                .collect(Collectors.toList());
        appUserRepository.removeAllByIdIn(passengersId);
        appUserRouteRepository.removeAllByUserIdIn(passengersId);

    }

    public DriversRouteDto getStreetNamesForDriver(String driverId) {
        AppUserRoute driver = appUserRouteRepository.getDriverById(driverId);
        List<String> streetNamesList = getStreetNamesList(driver);

        return DriversRouteDto
                .builder()
                .streetNames(streetNamesList)
                .startCoords(driver.getTravelFrom())
                .endCoords(driver.getTravelTo())
                .build();
    }

    private List<String> getStreetNamesList(AppUserRoute driver) {
        return Arrays.asList(driver.getTravelStreetList().split(","));
    }

    public void connectPassengerToDriver(final String passengerId, final String driverId) {

        AppMatchedPairs appMatchedPairs = new AppMatchedPairs();
        appMatchedPairs.setPassengerId(passengerId);
        appMatchedPairs.setDriverId(driverId);
        appMatchedPairs.setInsertDate(new Date());
        appMatchedPairsRepository.save(appMatchedPairs);
    }
}