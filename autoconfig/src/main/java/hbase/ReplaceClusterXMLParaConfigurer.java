package hbase;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import work.test.ExeternalPropertiesRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-08 17:59
 */
public class ReplaceClusterXMLParaConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(ReplaceClusterXMLParaConfigurer.class);

    private static boolean propertyOverride;

    private Properties properties = new Properties();

    private Document document;

    private String[] locations;

    private static final String DFS_CLIENT_FAILOVER_PROXY_PROVIDER = "dfs.client.failover.proxy.provider";

    public ReplaceClusterXMLParaConfigurer() {
    }

    @PostConstruct
    public void init() {
        // 从外部加载参数
        if (propertyOverride) {
            // TODO 从外部配置仓库获取参数
            properties = ExeternalPropertiesRepository.getProperties();
//            properties.setProperty("dfs.nameservices", "testValue");

            int length = locations.length;
            if (length != 0) {
                // TODO 异常处理
                try {
                    for (String loc : locations) {
                        replaceHadoopXMLParaValue(loc);
                    }
                } catch (Exception e) {
                    logger.error("read config exception:{}", e.getMessage());
                }
            }
        }
    }

    public void replaceHadoopXMLParaValue(String filePath) {
        // TODO 异常处理
        try {
            document = XmlUtil.readXML(filePath);
            Element rootElement = document.getRootElement();
            // 替换已存在的参数的值
            replaceElementContent(rootElement);
            // 替换指定key的值
            replaceHadoopXML_ProxyProvider(rootElement);
            // TODO 添加配置文件中不存在的参数

            XmlUtil.writer(document, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void replaceElementContent(Element e) {
        List<Element> propElements = e.elements("property");
        for (Element element : propElements) {
            // TODO null判断
            Element name = element.element("name");
            Element value = element.element("value");

            if (properties.containsKey(name.getTextTrim())) {
                value.setText(properties.getProperty(name.getTextTrim()));
            }
        }
    }

    public void replaceHadoopXML_ProxyProvider(Element e) {
            replaceParaByKeyRegExp(e, DFS_CLIENT_FAILOVER_PROXY_PROVIDER);
    }

    /**
     * 替换key中包含某一内容的参数的值
     * @param e
     * @param reg
     */
    public void replaceParaByKeyRegExp(Element e, String reg) {
        for (String propKey : properties.stringPropertyNames()) {
            propKey = propKey.trim();
            if (propKey.contains(reg)) {
                List<Element> propElements = e.elements("name");
                for (Element element : propElements) {
                    String name = element.getTextTrim();
                    if (!StringUtils.isEmpty(name)) {
                        if (name.contains(reg)) {
                            Element value = element.getParent().element("value");
                            value.setText(properties.getProperty(propKey));
                        }

                    }

                }

            }

        }
    }

    @Deprecated
//    public void addNotExistElement(Element e) {
//
//        for (String propKey : properties.stringPropertyNames()) {
//            propKey = propKey.trim();
//            List<Element> propElements = e.elements("name");
//            for (Element element : propElements) {
//                String name = element.getTextTrim();
//                if (propKey.equals(name)) {
//
//                }
//
//            }
//
//        }
//    }

    public boolean isPropertyOverride() {
        return propertyOverride;
    }

    public void setPropertyOverride(boolean propertyOverride) {
        ReplaceClusterXMLParaConfigurer.propertyOverride = propertyOverride;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String[] getLocations() {
        return locations;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }
}
