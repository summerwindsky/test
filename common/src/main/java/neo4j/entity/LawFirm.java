package neo4j.entity;

import lombok.Data;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-10-25 11:31
 */
@Data
public class LawFirm {
    private String id;
    private String name;
    private String xzqh;
    private String zyzh;
    private String lxdh;
    private String lxdz;
    private String fzr;
    private String lsrs;
//    private String jgzc;
    private String jgjj;
    private transient long createTime;
    private transient long updateTime;
    public LawFirm() {
    }

    public LawFirm(String id, String name, String xzqh, String zyzh, String lxdh, String lxdz, String fzr, String lsrs, String jgjj) {
        this.id = id;
        this.name = name;
        this.xzqh = xzqh;
        this.zyzh = zyzh;
        this.lxdh = lxdh;
        this.lxdz = lxdz;
        this.fzr = fzr;
        this.lsrs = lsrs;
//        this.jgzc = jgzc;
        this.jgjj = jgjj;
        this.createTime = System.currentTimeMillis();
    }
}
