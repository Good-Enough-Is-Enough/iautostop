package pl.waw.goodenough.iautostop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.waw.goodenough.iautostop.model.entity.AppUserRoute;

import java.util.Optional;

@Repository
public interface AppUserRouteRepository extends CrudRepository<AppUserRoute, String> {

        @Query("SELECT ur " +
            " FROM AppUserRoute ur" +
            " WHERE ur.userId= :id")
        Optional<AppUserRoute> findByUserId(@Param("id") final String id);
}