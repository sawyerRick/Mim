package cn.sawyer.mim.tool.result;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 16:13
 **/
public class Results {

    public static <T> Result<T> newResult(Code code, T data) {
        return new Result<>(code.code, code.desc, data);
    }

    public static <T> Result<T> newResult(Code code) {
        return new Result<>(code.code, code.desc);
    }
}
