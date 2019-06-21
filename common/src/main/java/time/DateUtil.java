package time;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static SimpleDateFormat sf = new SimpleDateFormat(DATE_TIME_FORMAT);

    /**
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
//			e.printStackTrace();
            logger.error("日期格式异常：{}", dateStr);
        }
        return date;
    }
    /**
     *
     * @param dateStr
     * @return
     */
    public static Date stringToDateNoSplit(String dateStr) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
//			e.printStackTrace();
            logger.error("日期格式异常：{}", dateStr);
        }
        return date;
    }

    public static long dateToLong(String dateStr) {

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
//			e.printStackTrace();
            logger.error("日期格式异常：{}", dateStr);
        }
        return date.getTime();
    }

    public static long dateToLong(String dateStr, String df) {

        if (StringUtils.isEmpty(dateStr)) {
            return 0;
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(df);
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
//			e.printStackTrace();
            logger.error("日期格式异常：{}", dateStr);
        }
        return date.getTime();
    }

    public static String getNowDate() {
        return sf.format(new Date());
    }

    public static String getNowDate(String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(new Date());
    }

    /**
     * 获取当前时间到传入时间的年限
     *
     * @param dateStr 传入时间 （在当前时间之前）
     * @return String 类型的年限间隔
     */
    public static String getYearTerm(String dateStr, String format) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date inputDate = sf.parse(dateStr);
        Calendar inputTime = Calendar.getInstance();
        inputTime.setTime(inputDate);

        Calendar currTime = Calendar.getInstance();

        if (currTime.before(inputTime)) {
            throw new IllegalArgumentException(
                    "传入时间大于当前时间");
        }

        int yearNow = currTime.get(Calendar.YEAR);
        int monthNow = currTime.get(Calendar.MONTH);
        int dayOfMonthNow = currTime.get(Calendar.DAY_OF_MONTH);

        int yearInput = inputTime.get(Calendar.YEAR);
        int monthInput = inputTime.get(Calendar.MONTH);
        int dayOfMonthInput = inputTime.get(Calendar.DAY_OF_MONTH);

        int term = yearNow - yearInput;

        if (monthNow <= monthInput) {
            if (monthNow == monthInput) {
                if (dayOfMonthNow < dayOfMonthInput) term--;
            }else{
                term--;
            }
        }
        return String.valueOf(term);
    }


    public static void main(String[] args) throws ParseException {
        System.out.println(DateUtil.getNowDate());

        System.out.println(sf.format(new Date(1558454231077L)));
        System.out.println(sf.format(new Date(1556617440010L)));
        System.out.println(sf.format(new Date(1557045001624L)));
    }
}
