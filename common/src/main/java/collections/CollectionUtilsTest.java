package collections;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.List;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-12-06 17:54
 */
public class CollectionUtilsTest {

    @Test
    public void testForAllDo() {
//        List<String> strings = Arrays.asList("1", "2");
        List<String> strings=null;
        CollectionUtils.forAllDo(strings, o ->{
            System.out.println(o);
        } );

    }
}
