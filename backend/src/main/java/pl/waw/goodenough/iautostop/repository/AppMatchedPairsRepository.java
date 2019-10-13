package pl.waw.goodenough.iautostop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.waw.goodenough.iautostop.model.entity.AppMatchedPairs;

import java.util.List;

@Repository
public interface AppMatchedPairsRepository extends CrudRepository<AppMatchedPairs, String> {

    @Query("SELECT mp " +
            "FROM AppMatchedPairs mp " +
            "WHERE mp.driverId = :driverId")
    List<AppMatchedPairs> selectAllByDriverId(@Param("driverId") String driverId);
}
