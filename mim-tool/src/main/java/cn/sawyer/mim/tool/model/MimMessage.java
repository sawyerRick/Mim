package cn.sawyer.mim.tool.model;

import cn.sawyer.mim.tool.constant.Constants;

import java.io.Serializable;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 17:01
 **/
public class MimMessage implements Serializable {

    private static final long serialVersionId = 1L;

    // 前64位，校验码，后64位，32位主版本号，32位次版本号
    private Integer crcCode = Constants.CRC_CODE;

    private Byte type; // 请求类型

    private Long userId; // userId

    private String username;

    private String msg;


    public static long getSerialVersionId() {
        return serialVersionId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCrcCode(Integer crcCode) {
        this.crcCode = crcCode;
    }


    @Override
    public String toString() {
        return "MimMessage{" +
                "crcCode=" + crcCode +
                ", type=" + type +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
