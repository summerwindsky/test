package work.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-04 14:47
 */
public class AppMain {
    public static void main(String[] args) {
        // 1.properties
        Properties properties = new Properties();
        properties.setProperty("test", "file");
        // 2.Configuration
        Configuration conf = HBaseConfiguration.create();
        conf.set("test", "file");
        conf.set("doc", "file");

        ApplicationContext context = new ClassPathXmlApplicationContext("springController.xml");
        PropertiesLoadController controller = (PropertiesLoadController) context.getBean("propertiesLoadControler");

        controller.buildProperty(properties, "setProperty");
        System.out.println("prop==="+properties.getProperty("test"));

        controller.buildProperty(conf, "set");
        System.out.println("conf==="+properties.getProperty("test"));

        System.out.println(PropertiesLoadController.getProperties().get("string1"));
        System.out.println(PropertiesLoadController.getProperties().get("string2"));
    }
}
