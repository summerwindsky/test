package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-07 18:09
 */
public class PropertyDTO {
    private String id;
    private String zjm;
    private String zjbb;
    private Map<String, String> parasRes = new HashMap(); // 返回的参数结果

    private String status; // 服务调用状态 200:成功, 400:
    private String statusMsg;

    public void setStatusSuccess() {
        this.setStatus("200");
        this.setStatusMsg("调用服务成功，参数获取正常");
    }

    public void setStatusRequestEmpty() {
        this.setStatus("300");
        this.setStatusMsg("http 请求的信息为空");
    }

    public void setStatusParaEmpty() {
        this.setStatus("400");
        this.setStatusMsg("组件参数为空");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZjm() {
        return zjm;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }

    public String getZjbb() {
        return zjbb;
    }

    public void setZjbb(String zjbb) {
        this.zjbb = zjbb;
    }

    public Map<String, String> getParasRes() {
        return parasRes;
    }

    public void setParasRes(Map<String, String> parasRes) {
        this.parasRes = parasRes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
