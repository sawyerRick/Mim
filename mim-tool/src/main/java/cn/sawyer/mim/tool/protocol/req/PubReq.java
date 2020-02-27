package cn.sawyer.mim.tool.protocol.req;

/**
 * @program: mim
 * @description: 群发请求
 * @author: sawyer
 * @create: 2020-02-27 16:25
 **/
public class PubReq {

    Long srcId;

    Long destId;

    String srcName;

    String msg;

    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
}
