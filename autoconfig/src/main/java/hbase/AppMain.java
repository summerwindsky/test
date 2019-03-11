package hbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-03 15:04
 */
public class AppMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springController.xml");
    }
}
