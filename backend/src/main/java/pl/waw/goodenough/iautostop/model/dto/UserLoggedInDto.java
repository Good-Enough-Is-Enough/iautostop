package pl.waw.goodenough.iautostop.model.dto;

import java.util.List;

public class UserLoggedInDto {

    private String UserId;
    private String roleType;
    private List<String> streets;

    public UserLoggedInDto(String userId, String roleType, List<String> streets) {
        UserId = userId;
        this.roleType = roleType;
        this.streets = streets;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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
