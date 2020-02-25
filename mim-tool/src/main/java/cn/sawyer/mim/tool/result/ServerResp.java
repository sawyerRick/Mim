package cn.sawyer.mim.tool.result;

import cn.sawyer.mim.tool.model.ServerInfo;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 14:32
 **/
public class ServerResp {

    private Integer code;

    private String desc;

    private ServerInfo data;

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

    public ServerInfo getServerInfo() {
        return data;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.data = serverInfo;
    }

    @Override
    public String toString() {
        return "ServerResp{" +
                "code=" + code +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
