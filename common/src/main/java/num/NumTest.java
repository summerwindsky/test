package num;

import org.apache.commons.lang.math.IntRange;
import org.junit.Test;

import java.util.Arrays;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-07-30 14:36
 */
public class NumTest {
    @Test
    public void testIn() {
        /**
         * null
         * null
         */
        Integer[] years = new Integer[2];
        Arrays.copyOfRange(years, 1, 4);
        for (Integer year : years) {
            System.out.println(year);
        }
    }

    @Test
    public void testRange() {
        IntRange intRange = new IntRange(1949, 2018);
        System.out.println(intRange.containsInteger(1948));

//        try {
//            Integer year = Integer.valueOf(010);
//            IntRange yearRange = new IntRange(1949, 2018);
//            if (yearRange.containsInteger(year)) {
//                ssnd = split[0];
//            }
//        } catch (NumberFormatException e) {
//            logger.error("诉讼年度转换异常,诉讼时间：{}", sssj);
//        }
    }

    @Test
    public void testWei() {
        int BATCH_UNIT = 1 << 10;
        int MAX_BATCH = 1 << 25;
        System.out.println(BATCH_UNIT);
        System.out.println(MAX_BATCH);

    }
}
