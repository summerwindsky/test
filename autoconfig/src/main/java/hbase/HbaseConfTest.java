package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-04 17:41
 */
public class HbaseConfTest {

    public static void main(String[] args) throws Exception {
        Configuration conf = HBaseConfiguration.create();
        ApplicationContext context = new ClassPathXmlApplicationContext("springController.xml");
        Newapi.createTable();

    }

}
