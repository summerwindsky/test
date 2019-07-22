package utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2019-06-24 11:26
 */
public class DateUtilTest {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static SimpleDateFormat sf = new SimpleDateFormat(DATE_TIME_FORMAT);

    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.getNowDate());

        System.out.println(sf.format(new Date(1560573140775L)));
    }
}

