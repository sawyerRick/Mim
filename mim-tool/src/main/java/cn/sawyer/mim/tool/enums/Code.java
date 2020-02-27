package cn.sawyer.mim.tool.enums;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 16:13
 **/
public enum Code {
    SUCCESS(200, "成功"),
    LOGIN_ERROR(3000, "登录错误"),
    REGISTRY_ERROR(4000, "注册错误"),
    DUPLICATE_LOGIN(5000, "重复登录"),
    DUPLICATE_REGISTRY(6000, "重复注册"),
    ;

    public Integer code;

    public String desc;

    Code(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                '}';
    }
}
