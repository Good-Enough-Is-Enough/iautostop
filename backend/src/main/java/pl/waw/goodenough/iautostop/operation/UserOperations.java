package pl.waw.goodenough.iautostop.operation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.model.entity.AppUser;
import pl.waw.goodenough.iautostop.model.entity.AppUserRoute;
import pl.waw.goodenough.iautostop.repository.AppUserRepository;
import pl.waw.goodenough.iautostop.repository.AppUserRouteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserOperations {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserRouteRepository appUserRouteRepository;

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

    public void createUser(final UserLoggedInDto userLoggedInDto) {

        AppUser appUser = new AppUser();
        appUser.setId(userLoggedInDto.getId());
        appUser.setRole(userLoggedInDto.getRole());

        appUserRepository.save(appUser);

        AppUserRoute appUserRoute = new AppUserRoute();
        appUserRoute.setUserId(userLoggedInDto.getId());
        appUserRoute.setTravelFrom(userLoggedInDto.getTravelFrom());
        appUserRoute.setTravelTo(userLoggedInDto.getTravelTo());
        appUserRouteRepository.save(appUserRoute);
    }
}
