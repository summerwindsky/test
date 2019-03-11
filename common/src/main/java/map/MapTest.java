package map;

import org.junit.Test;

import java.util.*;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-07-19 14:57
 */
public class MapTest {

    @Test
    public void testMap() {
        HashSet<String> set = new HashSet<String>();

        Map<String, String> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add(null);
//        Collection<String> values = map.values();
//        values = null;
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void testAddAll() {
        Map<String, String> map = new HashMap<>();
//        map.put("1", "v");
//        map.put("2", "v1");

        HashSet<String> set = new HashSet<>();
        set.addAll(map.values());
        for (String s : set) {
            System.out.println(s);
        }
    }

    @Test
    public void testHash() {
        int hashCode = "江苏省高级人民法院".hashCode();
        System.out.println(hashCode);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);
    }

}
