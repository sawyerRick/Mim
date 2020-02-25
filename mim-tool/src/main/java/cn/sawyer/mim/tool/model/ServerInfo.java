package cn.sawyer.mim.tool.model;

import java.io.Serializable;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 17:04
 **/
public final class ServerInfo implements Serializable {

    public ServerInfo() {
    }

    public ServerInfo(String server) {
        String[] segment = server.split(":");
        this.host = segment[0];
        this.httpPort = Integer.parseInt(segment[1]);
        this.msgPort = Integer.parseInt(segment[2]);
    }

    public ServerInfo(String host, Integer httpPort, Integer msgPort) {
        this.host = host;
        this.httpPort = httpPort;
        this.msgPort = msgPort;
    }

    private static final long serialVersionId = 1L;

    private String host;

    private Integer httpPort;

    private Integer msgPort;

    public static long getSerialVersionId() {
        return serialVersionId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    public Integer getMsgPort() {
        return msgPort;
    }

    public void setMsgPort(Integer msgPort) {
        this.msgPort = msgPort;
    }

    @Override
    public String toString() {
        return host + ":" + httpPort + ":" + msgPort;
    }
}
