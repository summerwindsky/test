package prop;

import xml.SysConfig;

import java.util.Properties;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-14 16:19
 */
public class PrintUtil {
    public static void main(String[] args) {
        Properties props = SysConfig.getProps();
        for (Object key : props.keySet()) {
            String k = (String)key;
            System.out.println(k);
        }
    }
}
