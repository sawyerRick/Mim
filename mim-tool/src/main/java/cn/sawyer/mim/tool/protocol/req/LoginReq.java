package cn.sawyer.mim.tool.protocol.req;

/**
 * @program: mim
 * @description: 登录请求
 * @author: sawyer
 * @create: 2020-02-27 15:47
 **/
public class LoginReq {

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
        return "LoginReq{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
