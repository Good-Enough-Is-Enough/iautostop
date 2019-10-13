package pl.waw.goodenough.iautostop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.waw.goodenough.iautostop.model.entity.AppMatchedPair;

import java.util.List;

@Repository
public interface AppMatchedPairsRepository extends CrudRepository<AppMatchedPair, String> {

    @Query("SELECT mp " +
            "FROM AppMatchedPair mp " +
            "WHERE mp.driverId = :driverId")
    List<AppMatchedPair> selectAllByDriverId(@Param("driverId") String driverId);
}
