package utils.json;

import org.junit.Test;

import java.util.List;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2019-01-11 14:19
 */
public class ParseJson {

    /**
     * status : ok
     * ret : [["人民法庭","6个"],["内设机构","审判管理办公室、研究室等"],["年收案数","逾5000件"],["中文名","桐城市人民法院"],["CATEGORY_ZH","组织机构"],["DESC","桐城市人民法院现有在编在岗干警98人，聘用工作人员25人。设有政治处、办公室、监察室、审判管理办公室、研究室、刑事审判庭、执行局（庭）等18个内设机构以及孔城法庭、卅铺法庭、范岗法庭、金神法庭、双港法庭、青草法庭六个基层人民法庭。近几年，年收案数逾5000件，数量位居安徽省基层法院前列。\n桐城市人民法院案件上诉率、发回重审率、改判率在安庆市基层法院中处于较低水平，显示出过硬的办案质量。与此同时，桐城法院始终坚持\u201c三个至上\u201d的工作宗旨，积极参与社会管理创新，着力化解社会矛盾纠纷，坚持廉洁公正司法，积极服务于桐城市经济社会发展大局，法院工作受到桐城市委、上级法院和人民群众的高度肯定。"]]
     */

    private String status;
    private List<List<String>> ret;

    @Test
    public void test2Map() {
        String json = "{\n" +
                "    \"status\":\"ok\",\n" +
                "    \"ret\":[\n" +
                "        [\n" +
                "            \"人民法庭\",\n" +
                "            \"6个\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"内设机构\",\n" +
                "            \"审判管理办公室、研究室等\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"年收案数\",\n" +
                "            \"逾5000件\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"中文名\",\n" +
                "            \"桐城市人民法院\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"CATEGORY_ZH\",\n" +
                "            \"组织机构\"\n" +
                "        ],\n" +
                "        [\n" +
                "            \"DESC\",\n" +
                "            \"桐城市人民法院现有在编在岗干警98人，聘用工作人员25人。设有政治处、办公室、监察室、审判管理办公室、研究室、刑事审判庭、执行局（庭）等18个内设机构以及孔城法庭、卅铺法庭、范岗法庭、金神法庭、双港法庭、青草法庭六个基层人民法庭。近几年，年收案数逾5000件，数量位居安徽省基层法院前列。\n" +
                "桐城市人民法院案件上诉率、发回重审率、改判率在安庆市基层法院中处于较低水平，显示出过硬的办案质量。与此同时，桐城法院始终坚持“三个至上”的工作宗旨，积极参与社会管理创新，着力化解社会矛盾纠纷，坚持廉洁公正司法，积极服务于桐城市经济社会发展大局，法院工作受到桐城市委、上级法院和人民群众的高度肯定。\"\n" +
                "        ]\n" +
                "    ]\n" +
                "}";
        ParseJson ParseJson = GsonUtil.fromJson(json, ParseJson.class);



    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<List<String>> getRet() {
        return ret;
    }

    public void setRet(List<List<String>> ret) {
        this.ret = ret;
    }
}
