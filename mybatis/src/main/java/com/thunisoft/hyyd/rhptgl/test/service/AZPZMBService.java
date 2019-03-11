package com.thunisoft.hyyd.rhptgl.test.service;

import com.thunisoft.hyyd.rhptgl.test.dao.AZPZMBMapper;
import com.thunisoft.hyyd.rhptgl.test.dao.AZPZMBSqlProvider;
import com.thunisoft.hyyd.rhptgl.test.model.AZPZMB;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-05-11 18:05
 */
public class AZPZMBService {

    AZPZMBSqlProvider azpzmbSqlProvider;

    AZPZMBMapper azpzmbMapper;

    public void test() {
        AZPZMB azpzmb = new AZPZMB();
        azpzmb.setcBb("1.0");
        azpzmbMapper.insert(azpzmb);
    }
}
