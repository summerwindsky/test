package util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This example demonstrates the use of the {@link ResponseHandler} to simplify
 * the process of processing the HTTP response and releasing associated resources.
 */
public class ClientWithResponseHandler {

    private static final Logger logger = LoggerFactory.getLogger(ClientWithResponseHandler.class);

    public String getRequest(String url, Map<String, String> paras) {
        CloseableHttpClient httpclient = null;
        String responseBody = null;
        try {
            httpclient = HttpClients.createDefault();
            //请求超时时间 毫秒
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(10000).setConnectionRequestTimeout(10000)
                    .setSocketTimeout(10000).build();
//            HttpGet httpget = new HttpGet(url);
//            httpget.setConfig(requestConfig);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            List<NameValuePair> nvps = new ArrayList<>();
            for (String key : paras.keySet()) {
                nvps.add(new BasicNameValuePair(key, paras.get(key)));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//            System.out.println("Executing request " + httpget.getRequestLine());
            // Create a custom response handler
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            responseBody = httpclient.execute(httpPost, responseHandler);
            return responseBody;
        } catch (Exception e) {
            logger.error("发送" + url + "请求失败:" + e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (Exception e) {
                logger.error("关闭httpclient发生异常:" + e.getMessage());
            }
            return responseBody;
        }
    }

}
