package jdk.collections;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.LinkedList;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-12-06 16:00
 */
public class LinkedListTest {
    @Test
    public void testSort() {
        getNotNullTKX(new LinkedList<>(), "1", "3", "2","4");
    }


    @Test
    public void testIndex() {
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.add(4);

        linkedList.remove(2);
        System.out.println(linkedList.get(2));// 4

    }

    private void getNotNullTKX(LinkedList<String> tkxList, String transT, String transK, String transX, String transM) {
        if (StringUtils.isNotEmpty(transT)) {
            tkxList.add(transT);
        }
        if (StringUtils.isNotEmpty(transK)) {
            tkxList.add(transK);
        }
        if (StringUtils.isNotEmpty(transX)) {
            tkxList.add(transX);
        }
        if (StringUtils.isNotEmpty(transM)) {
            tkxList.add(transM);
        }
        for (String s : tkxList) {
            System.out.println(s);
        }
    }
}
