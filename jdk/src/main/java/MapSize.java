import memory.ClassIntrospector;
import memory.ObjectInfo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-08-28 14:02
 */
public class MapSize {
    public static void main(String[] args) throws IllegalAccessException {
        Map map = new HashMap<String, String>();
        Set<String> set = new HashSet<>();
        int i = 0;
        while (true) {
//            set.add("陈为民##陕西浩元律师事务所陕西浩元律师事务所" + i);
            map.put("065ae3ac53859906f47f7c099bcab208e5d" + i, "005c9cad79252dd9bbb225ef6683157fb8a" + i);
            if (i > 100000) {
                final ClassIntrospector ci = new ClassIntrospector();
                ObjectInfo res = ci.introspect(set);
                ObjectInfo res1 = ci.introspect(map);
                System.out.println( res.getDeepSize() );
                System.out.println( res1.getDeepSize() );
                break;
            }
            i++;
        }
    }
}
