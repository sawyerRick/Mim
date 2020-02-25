package cn.sawyer.mim.tool.model;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 16:09
 **/
public class UserInfo {

    public UserInfo() {
    }

    public UserInfo(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    Long userId;

    String username;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
