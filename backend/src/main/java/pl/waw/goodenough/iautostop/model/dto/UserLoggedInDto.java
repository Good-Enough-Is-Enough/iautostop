package pl.waw.goodenough.iautostop.model.dto;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.List;

public class UserLoggedInDto {

    private String userId;
    private String roleType;
    private List<String> streets;

    public UserLoggedInDto(String userId, String roleType, List<String> streets) {
        this.userId = userId;
        this.roleType = roleType;
        this.streets = streets;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<String> getStreets() {
        return streets;
    }

    public void setStreets(List<String> streets) {
        this.streets = streets;
    }
}
