package pl.waw.goodenough.iautostop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.waw.goodenough.iautostop.model.entity.AppUser;

import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, String> {

    @Query("SELECT u " +
            " FROM AppUser u" +
            " WHERE u.id= :id")
    Optional<AppUser> findByUserId(@Param("id") final String id);
}