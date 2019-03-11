package util;

import java.util.HashMap;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-07 11:12
 */
public class HttpClientTest {
    public static void main(String[] args) {
        ClientWithResponseHandler client = new ClientWithResponseHandler();
        // http://192.168.1.67:5088/importCase/run/
        HashMap<String, String> map = new HashMap<>();
        map.put("propDTOJson", "{\"zjm\":\"importCase\",\"zjbb\":\"1.0\",\"parasRes\":{\"paraKey1\":\"paraValue2\",\"paraKey\":\"paraValue\"},\"status\":\"status\",\"statusMsg\":\"statusMsg\"}");
        String res = client.getRequest(new String("http://localhost:8090/rhptgl/getProp"), map);
        System.out.println(res);
    }
}
