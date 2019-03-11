package work.test;

import model.PropertyDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.http.HttpService;

import javax.annotation.PostConstruct;
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
public class ExeternalPropertiesRepository {

    private static final Logger logger = LoggerFactory.getLogger(ExeternalPropertiesRepository.class);

    private static boolean propertyOverride ;

    private static Properties properties = new Properties();

    private PropertyDTO propertyDTO = new PropertyDTO();

    public ExeternalPropertiesRepository() {
    }

    @PostConstruct
    public void init() {
        // 从外部加载参数
        if (propertyOverride) {
            // 调外部服务，获取参数
            Map<String, String> paras = null;
            // TODO 异常控制
            try {
                paras = HttpService.visitServ(propertyDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            properties.putAll(paras);
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void setProperties(Properties properties) {
        ExeternalPropertiesRepository.properties = properties;
    }

    public boolean isPropertyOverride() {
        return propertyOverride;
    }

    public void setPropertyOverride(boolean propertyOverride) {
        this.propertyOverride = propertyOverride;
    }
}
