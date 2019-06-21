package neo4j.entity;

import lombok.Data;
import neo4j.util.EncodeUtils;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-10-25 15:04
 */
@Data
public class RefLawyer2Ls {
    private String id;
    private String lawyerId;
    private String lsId;
    private String name;

    public RefLawyer2Ls(String lawyerId, String lsId) {
        this.id = EncodeUtils.randomUuid();
        this.lawyerId = lawyerId;
        this.lsId = lsId;
        this.name = "就职";
    }
}
