package pl.waw.goodenough.iautostop.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
public class AppMatchedPair {

    String passengerId;
    String driverId;
    Date insertDate;
}
