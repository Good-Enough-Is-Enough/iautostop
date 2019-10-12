package pl.waw.goodenough.iautostop.model.dto;

import java.util.List;

public class UserLoggedInDto {

    private String userId;
    private String roleType;
    private String start;
    private String end;

    public UserLoggedInDto(String userId, String roleType, String start, String end) {
        this.userId = userId;
        this.roleType = roleType;
        this.start = start;
        this.end = end;
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

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
