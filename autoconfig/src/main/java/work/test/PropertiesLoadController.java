package work.test;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-04 10:41
 */
public class PropertiesLoadController {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesLoadController.class);

    private static boolean propertyOverride ;

    private static Map<String, String> propertiesMap = new HashMap();
    private static Properties properties = new Properties();

    private static Resource[] locations;

    public PropertiesLoadController() {
    }

    @PostConstruct
    public void init() {
        // 从外部加载参数
        if (propertyOverride) {
            // TODO 获取外部配置
            properties.put("test", "external");
        } else {
            int length = locations.length;
            if (length != 0) {
                InputStream fis = null;
                try {
                    for (Resource loc : locations) {
                        fis = loc.getInputStream();
                        properties.load(new InputStreamReader(fis, "UTF-8"));
                    }
                } catch (Exception e) {
                    logger.error("read config exception:{}", e.getMessage());
                } finally {
                    IOUtils.closeQuietly(fis);
                }
            }
        }
    }

    /**
     *
     * @param obj
     * @param setMethodName
     */
    public void buildProperty(Object obj, String setMethodName) {
        Class<?> classType = obj.getClass();
        try {
            Method setMethod = classType.getDeclaredMethod(setMethodName, new Class[]{String.class, String.class});
            for (String key : properties.stringPropertyNames()) {
                setMethod.invoke(obj, key, properties.get(key));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        PropertiesLoadController.properties = properties;
    }

    public Resource[] getLocations() {
        return locations;
    }

    public void setLocations(Resource... locations) {
        this.locations = locations;
    }

    public boolean isPropertyOverride() {
        return propertyOverride;
    }

    public void setPropertyOverride(boolean propertyOverride) {
        this.propertyOverride = propertyOverride;
    }
}
