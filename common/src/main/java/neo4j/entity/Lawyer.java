package neo4j.entity;

import lombok.Data;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-10-25 11:26
 */
@Data
public class Lawyer {
    private String id;
    private String name;
    private String lsmc;
    private String xzqh;
    private String zyzh;
    private String sfzh;
//    private String zgzh;
    private String zynx;
//    private String fzrq;
//    private String tel;
    private String webUrl;
    private transient long createTime;
    private transient long updateTime;

    public Lawyer(String id, String name, String lsmc, String xzqh, String zyzh, String sfzh, String zynx, String webUrl) {
        this.id = id;
        this.name = name;
        this.lsmc = lsmc;
        this.xzqh = xzqh;
        this.zyzh = zyzh;
        this.sfzh = sfzh;
        this.zynx = zynx;
        this.webUrl = webUrl;
        this.createTime = System.currentTimeMillis();
    }
}
