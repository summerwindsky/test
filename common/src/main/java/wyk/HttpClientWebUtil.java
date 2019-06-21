package wyk;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HttpClient请求工具类
 */
public class HttpClientWebUtil {
    protected static final Logger log = Logger.getLogger(HttpClientWebUtil.class);

    // 请求超时
    private static final int HTTP_Request_TIMEOUT = 2000;
    // 连接超时
    private static final int HTTP_Connetion_TIMEOUT = 2000;
    // 请求编码
    private static String encode = "UTF-8";

    // 设置连接超时与请求超时，暂时不设置
    private void setOutTime(HttpClient httpClient) {
        // HttpConnectionManagerParams managerParams =httpClient.getHttpConnectionManager().getParams();
        // 设置连接超时时间(单位毫秒)
        // managerParams.setConnectionTimeout(30000);
        // 设置读数据超时时间(单位毫秒)
        // managerParams.setSoTimeout(120000);
    }

    /**
     * post请求
     *
     * @param requestUrl
     * @param requestMap
     * @return 成功responseBody字符串，失败返回null
     * @throws Exception
     */
    public static String doHttpPost(String requestUrl, Map<String, String> authInfo, Map<String, String> headerInfo, Map<String, Object> requestMap) throws Exception {
        // 创建默认的客户端实例

        DefaultHttpClient client = new DefaultHttpClient();

        //添加证书
        authenticationProcess(authInfo, client);

        // 创建客户端实例
        HttpPost post = new HttpPost(requestUrl);

        //设置请求头参数
        if (headerInfo != null) {
            Iterator<Entry<String, String>> iterator = headerInfo.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                post.setHeader(key, value);
            }
        }

        // 设置请求参数
        if (requestMap != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("params", GsonUtil.toJson(requestMap)));
            post.setEntity(new UrlEncodedFormEntity(nvps, encode));
        }

        try {
            // 进行请求
            HttpResponse httpResponse = client.execute(post);
            // 判断返回状态（200状态码，代表请求成功）
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity());
                // 关闭连接
                client.getConnectionManager().shutdown();
                return responseBody;
            }
            log.info("返回状态码：" + httpResponse.getStatusLine().getStatusCode());
            return null;
        } catch (Exception e) {
            // 关闭连接
            client.getConnectionManager().shutdown();
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * put请求
     *
     * @param requestUrl
     * @param authInfo   验证信息
     * @param headerInfo 消息头信息
     * @param requestMap 请求信息
     * @return 成功responseBody字符串，失败返回null
     * @throws Exception
     */
    public static String doHttpPut(String requestUrl, Map<String, String> authInfo, Map<String, String> headerInfo, String requestMap) throws Exception {
        // 创建默认的客户端实例
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 120000);
        HttpConnectionParams.setSoTimeout(httpParams, 120000);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);

        //添加证书
        authenticationProcess(authInfo, client);

        // 创建客户端实例
        HttpPut put = new HttpPut(requestUrl);

        //设置请求头参数
        if (headerInfo != null) {
            Iterator<Entry<String, String>> iterator = headerInfo.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                String key = entry.getKey();
                String value = entry.getValue();
                put.setHeader(key, value);
            }
        }


        // 设置请求参数
        if (requestMap != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("params", requestMap));
            put.setEntity(new UrlEncodedFormEntity(nvps, encode));


            System.out.println(requestMap);
        }

        try {
            // 进行请求
            HttpResponse httpResponse = client.execute(put);

            System.out.println(EntityUtils.toString(httpResponse.getEntity()));

            // 判断返回状态（200状态码，代表请求成功）
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity());
                // 关闭连接
                client.getConnectionManager().shutdown();
                return responseBody;
            }
            System.out.println(httpResponse.getStatusLine());
            System.out.println(httpResponse.getStatusLine().getReasonPhrase());
            log.info("返回状态码：" + httpResponse.getStatusLine().getStatusCode());
            return null;
        } catch (Exception e) {
            // 关闭连接
            client.getConnectionManager().shutdown();
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * get请求
     *
     * @param requestUrl
     * @param authInfo
     * @param requestMap
     * @return 返回responseBody字符串
     * @throws Exception
     */
    public static String doHttpGet(String requestUrl, Map<String, String> authInfo, Map<String, Object> requestMap) throws Exception {
        // 创建默认的客户端实例
        DefaultHttpClient client = new DefaultHttpClient();

        //添加证书
        authenticationProcess(authInfo, client);

        // 创建客户端实例
        HttpGet get = new HttpGet(requestUrl);
        // 设置请求参数,拼接参数
        if (requestMap != null) {
            requestUrl = requestUrl + "?params=" + GsonUtil.toJson(requestMap);
        }
        // 实例化HTTP方法
        HttpGet request = new HttpGet();
        request.setURI(new URI(requestUrl));
        try {
            // 进行请求
            HttpResponse httpResponse = client.execute(get);
            // 判断返回状态（200状态码，代表请求成功）
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseBody = EntityUtils.toString(httpResponse.getEntity());
                // 关闭连接
                client.getConnectionManager().shutdown();
                return responseBody;
            }
            return null;
        } catch (Exception e) {
            // 关闭连接
            client.getConnectionManager().shutdown();
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 添加验证信息
     *
     * @param authInfo 验证map
     * @param client   http客户端
     * @return
     */
    public static Map<String, String> authenticationProcess(Map<String, String> authInfo, DefaultHttpClient client) {
        if (authInfo != null && authInfo.size() > 0) {
            String host = authInfo.get("host");
            int port = Integer.parseInt(authInfo.get("port"));
            String userName = authInfo.get("userName");
            String password = authInfo.get("password");
            client.getCredentialsProvider().setCredentials(
                    new AuthScope(host, port),
                    new UsernamePasswordCredentials(userName, password));
        }
        return authInfo;
    }

}
