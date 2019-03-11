package entity;

import lombok.Data;
import util.EncodeUtils;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-10-25 15:05
 */
@Data
public class RefLs2Ws {
    private String id;
    private String lsId;
    private String wsId;
    private String name;

    public RefLs2Ws(String lsId, String wsId) {
        this.id = EncodeUtils.randomUuid();
        this.lsId = lsId;
        this.wsId = wsId;
        this.name = "代理";
    }
}
