//package http;
//
//import json.GsonUtil;
//import lombok.extern.log4j.Log4j2;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//
///**
// * Title:
// * Description:
// * Company: 北京华宇元典信息服务有限公司
// *
// * @author zhangjing
// * @version 1.0
// * @date 2019-02-11 10:43
// */
//@Log4j2
//public class HttpTest {
//
//    public Flfg restGet(String url, String flmc, String errMsg) {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        Flfg flfg = null;
//        try {
//            URIBuilder uriBuilder = new URIBuilder("http://172.16.124.9:8090/boot/fb/ft");
//            uriBuilder.addParameter("flmc", flmc);
//            HttpGet httpGet = new HttpGet(uriBuilder.build());
//            CloseableHttpResponse response = httpclient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            String result = EntityUtils.toString(entity);
//            flfg = GsonUtil.fromJson(result, Flfg.class);
////            logger.info("触发知识图谱遍历律师律所成功！URI为：" + "");
//        } catch (Exception e) {
//            logger.error("调法宝接口失败！", e);
//        } finally {
//            try {
//                httpclient.close();
//            } catch (IOException e) {
//                logger.error("调法宝接口，关闭httpclient失败！", e);
//            }
//        }
//        return flfg;
//    }
//}
