package pl.waw.goodenough.iautostop.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class AppMatchedPairs {

    @Id
    String passengerId;
    String driverId;
    Date insertDate;
}
