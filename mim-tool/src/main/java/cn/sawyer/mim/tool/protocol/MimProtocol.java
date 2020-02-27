package cn.sawyer.mim.tool.protocol;

import cn.sawyer.mim.tool.enums.MsgType;

import java.io.Serializable;

/**
 * @program: mim
 * @description: 私有协议栈
 * @author: sawyer
 * @create: 2020-02-26 23:46
 **/
public class MimProtocol implements Serializable {

    Long srcId;

    String srcName;

    Long destId;

    String msg;

    MsgType type;

    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public Long getDestId() {
        return destId;
    }

    public void setDestId(Long destId) {
        this.destId = destId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MimProtocol{" +
                "srcId=" + srcId +
                ", srcName='" + srcName + '\'' +
                ", destId=" + destId +
                ", msg='" + msg + '\'' +
                ", type=" + type +
                '}';
    }
}
