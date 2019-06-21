package neo4j.util.http;

import hbase.xml.ResourcePath;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-08 9:47
 */
public class HttpFactory {

    private static final Logger logger = LoggerFactory.getLogger(HttpFactory.class);

    private String propPath = "http.properties";
    private static HttpFactory instance = null;
    private static final Object lock = new Object();

    private Properties properties = new Properties();

    private HttpFactory() {
        FileInputStream fis = null;
        try {
            String configPath = ResourcePath.getClassPath() + File.separator + propPath;
            fis = new FileInputStream(new File(configPath));// 属性文件输入流
            properties.load(new InputStreamReader(fis, "UTF-8"));
            init();
        } catch (Exception e) {
            logger.error("read config exception:{}", e.getMessage());
        } finally {
            IOUtils.closeQuietly(fis);
        }
    }

    private HttpClient client = null;
    private boolean inited = false;

    public static HttpFactory getInstance() {
        synchronized (lock) {
            if (null == instance) {
                instance = new HttpFactory();
            }
        }
        return instance;
    }

    public void init() throws IOException {
        synchronized (lock) {
            MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
            client = new HttpClient(connectionManager);

            String serverHost = properties.getProperty("http.host");
            String charset = properties.getProperty("http.charset");
            charset = (null == charset || charset.trim().length() <= 0) ? "utf-8" : charset;

            Integer serverPort = Integer.valueOf(properties.getProperty("http.port"));
            Integer clientTimeout = Integer.valueOf(properties.getProperty("http.timeout"));

            client.getHostConfiguration().setHost(serverHost, serverPort, "http");
            client.getParams().setParameter("http.protocol.content.charset", charset);
            client.getParams().setContentCharset(charset);
            client.getParams().setSoTimeout(clientTimeout);

            inited = true;
        }
    }

    public HttpClient getClient() throws Exception {
        synchronized (lock) {
            if (!inited) {
                throw new Exception("http client 没有初始化");
            }
            return client;
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
