package cn.sawyer.mim.tool.constant;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 17:34
 **/
public class Constants {

    /**
     * ZooKeeper可用服务器节点
     */
    public static final String ZK_SV_ROOT = "/route";


    /**
     * 用户Hash前缀
     */
    public static final String ACCOUNT_PREFIX = "MIM_ACCOUNT:";

    /**
     * Redis hash key，k用户id,v服务器
     */
    public static final String ROUTED_KEY = "MIM_ROUTE";

    /**
     * Redis string key，在线用户
     */
    public static final String ONLINE_KEY = "MIM_ONLINE";

    /**
     * 校验码
     */
    public static final Integer CRC_CODE = 0xBABE0101;
}
