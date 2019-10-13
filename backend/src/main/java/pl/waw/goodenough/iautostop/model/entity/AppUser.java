package pl.waw.goodenough.iautostop.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class AppUser {

    @Id
    private String id;
    private String role;
    private String name;
    private String phone;
}
