package pl.waw.goodenough.iautostop.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UserRoute {

    @Id
    private String userId;
    private String travelFrom;
    private String travelTo;
    private String travelStreetList;
}
