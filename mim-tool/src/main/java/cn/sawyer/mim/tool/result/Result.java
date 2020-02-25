package cn.sawyer.mim.tool.result;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 16:12
 **/
public class Result<T> {

    public Result() {
    }

    public Result(Integer code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Result(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private Integer code;

    private String desc;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
