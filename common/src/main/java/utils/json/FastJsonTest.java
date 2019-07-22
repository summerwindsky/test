package utils.json;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-07-17 16:45
 */
public class FastJsonTest {

    private String s = "{\n" +
            "\t\"msg\":\"获取成功\",\n" +
            "\t\"result\":{\n" +
            "\t\t\"appid\":\"hyyd_data\",\n" +
            "\t\t\"id\":100,\n" +
            "\t\t\"token\":\"0y6Kl6TUN90RV25dbM6hhHV0FyZg296p\"\n" +
            "\t},\n" +
            "\t\"success\":true\n" +
            "}";

    @Test
    public void testNull() {

        Object parse = JSONObject.parse(s);
        System.out.println(parse);
    }
}
