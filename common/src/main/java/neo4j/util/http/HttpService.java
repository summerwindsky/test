//package neo4j.util.http;
//
//import com.google.gson.Gson;
//import model.PropertyDTO;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.methods.PostMethod;
//
//import java.util.Map;
//
///**
// * Title:
// * Description: 客户端的服务类，用于封装请求，解析返回的json串
// * Company: 北京华宇元典信息服务有限公司
// *
// * @author zhangjing
// * @version 1.0
// * @date 2018-05-08 10:08
// */
//public class HttpService {
//
//    private static String GETPROP_URL = "/rhptgl/getProp";
//    private static Gson gson = new Gson();
//
//    public static Map<String, String> visitServ(PropertyDTO dto) throws Exception {
//
//        Map<String, String> parasRes = null;
//
//        HttpClient client = HttpFactory.getInstance().getClient();
//
//        String propDTOJson = gson.toJson(dto, PropertyDTO.class);
//        PostMethod post = new PostMethod(GETPROP_URL);
//        post.addParameter("propDTOJson", propDTOJson);
//
//        // 调外部服务，获取参数配置
//        client.executeMethod(post);
//        String resStr = post.getResponseBodyAsString();
//        PropertyDTO propDTORes = gson.fromJson(resStr, PropertyDTO.class);
//
//        String status = propDTORes.getStatus();
//        String statusMsg = propDTORes.getStatusMsg();
//        if ("200".equals(status)) {
//            parasRes = propDTORes.getParasRes();
//        } else {
//            // TODO 异常控制，何种执行状态抛异常
//            throw new RuntimeException("执行http请求异常，状态码为:[" + status + "] | 异常信息为:" + statusMsg);
//        }
//        return parasRes;
//    }
//
//}
