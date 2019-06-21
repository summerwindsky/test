package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-11-09 16:57
 */
public class PhoenixTest {
    public static void main(String[] args) throws SQLException {
///////////// 测试Phoenix连接 ///////////////
//        String testSQL = "UPSERT INTO \"hyyd_ajbsbg_1109\"(\"ajbs\", \"m\".\"updatetime\", \"m\".\"option\") values('6e599281-aeee-4cff-ac3c-50190f29139d', 1541753819845, '1')";
//        String testSQL = "UPSERT INTO \"hyyd_ajbsbg_1109\"(\"ajbs\", \"m\".\"updatetime\", \"m\".\"option\") values('de0b2ba6-37ad-403f-810a-f45058aff3ce', 1541753819845, '1')";
        String testSQL = "UPSERT INTO \"hyyd_ajbsbg_1109\"(\"ajbs\", \"m\".\"updatetime\", \"m\".\"option\") values('60e974da-0f30-4495-be93-e09cf36a20b2', 1541753819845, '1')";
        try {
            Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");
        } catch (ClassNotFoundException e1) {
            System.out.println("org.apache.phoenix.jdbc.PhoenixDriver未找到");
        }
//        List<Integer> resList = new ArrayList<Integer>();
        Connection con1 = DriverManager.getConnection("jdbc:phoenix:172.16.124.8:2181","","");
        Statement stmt = con1.createStatement();
        boolean rset = stmt.execute(testSQL);
        System.out.println(rset);
//        System.out.println(resList.size());
        con1.commit();
        stmt.close();
        con1.close();
    }
}
