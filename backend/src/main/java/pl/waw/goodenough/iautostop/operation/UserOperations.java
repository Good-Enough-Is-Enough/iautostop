package pl.waw.goodenough.iautostop.operation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import pl.waw.goodenough.iautostop.model.dto.UserLoggedInDto;
import pl.waw.goodenough.iautostop.model.entity.User;
import pl.waw.goodenough.iautostop.model.entity.UserRoute;
import pl.waw.goodenough.iautostop.repository.UserRepository;
import pl.waw.goodenough.iautostop.repository.UserRouteRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserOperations {

    private UserRepository userRepository;
    private UserRouteRepository userRouteRepository;

    public UserLoggedInDto getUser(final String id) {

        Optional<UserRoute> userRoute = userRouteRepository.findById(id);
        Optional<User> user = userRepository.findById(id);

        if (userRoute.isPresent() && user.isPresent()) {
            UserRoute ur = userRoute.get();
            User u = user.get();

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
}
