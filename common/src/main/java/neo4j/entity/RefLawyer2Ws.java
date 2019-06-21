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
 * @date 2018-10-25 15:03
 */
@Data
public class RefLawyer2Ws {
    private String id;
    private String lawyerId;
    private String wsId;
    private String name;

    public RefLawyer2Ws(String lawyerId, String wsId) {
        this.id = EncodeUtils.randomUuid();
        this.lawyerId = lawyerId;
        this.wsId = wsId;
        this.name = "代理";
    }
}
