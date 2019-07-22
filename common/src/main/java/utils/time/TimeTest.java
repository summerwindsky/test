package utils.time;

import org.junit.Test;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-11-08 14:20
 */
public class TimeTest {
    @Test
    public void createStamp() {
        Long oldStamp = 1541657839751L;
        System.out.println(System.currentTimeMillis() - oldStamp);
    }

    @Test
    public void testHour() {
        Long stamp = 1541657839751L;
        System.out.println(System.currentTimeMillis()/(60*60));
    }
}
