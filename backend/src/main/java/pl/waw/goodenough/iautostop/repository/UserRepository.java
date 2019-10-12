package pl.waw.goodenough.iautostop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.waw.goodenough.iautostop.model.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<pl.waw.goodenough.iautostop.model.entity.User, String> {

}