package cn.sawyer.mim.tool.result;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 20:23
 **/
public enum MsgType {
    MSG_REQ((byte) 0),
    MSG_RESP((byte) 1),
    ONE_WAY((byte) 2),
    HANDSHAKE_REQ((byte) 3),
    HANDSHAKE_RESP((byte) 4),
    HEARTBEAT_REQ((byte) 5),
    HEARTBEAT_RESP((byte) 6);

    private byte value;

    private MsgType(byte value) {
        this.value = value;
    }

    public byte value() {
        return this.value;
    }
}