package utils.xml;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 从properties的文件中获取相应的属性
 * Title: SysConfig
 * Description: 
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author liuxw
 * @version 1.0
 * @date 2017-5-4 下午2:19:43
 *
 */
public class SysConfig {

    private static final Logger logger = LoggerFactory.getLogger(SysConfig.class);

    private static SysConfig instance = new SysConfig();

//    private static final String propsName = "config.properties";
    private static final String propsName = "ws-hbase.properties";

    private Properties props = new Properties();

    private SysConfig() {
        FileInputStream fis = null;
        try {
            String configPath = ResourcePath.getClassPath() + File.separator + propsName;
            fis = new FileInputStream(new File(configPath));// 属性文件输入流
            props.load(new InputStreamReader(fis, "UTF-8"));
        } catch (Exception e) {
            logger.error("read config exception:{}", e.getMessage());
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    public static Properties getProps() {
        return instance.props;
    }
    /**
     * 获取properties中的key值指定的属性
     * @param key
     * @return
     */
    public static String getProp(String key) {
        return instance.props.getProperty(key);
    }
    /**
     * 获取properties中key值指定的属性，默认值为defaultValue
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProp(String key, String defaultValue) {
        return instance.props.getProperty(key, defaultValue);
    }

}
