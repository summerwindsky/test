package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.junit.Test;

import java.util.Random;

public class Newapi {
    static Admin hBaseAdmin;
    static Table hTable;

    // 表名
    static String TN = "tbl";


    public static void main(String[] args) throws Exception {
        Newapi.createTable();
    }

    public static void setup() throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
//        putHdfsxml(conf);
        Connection connection = ConnectionFactory.createConnection(conf);
//        org.apache.hadoop.hbase.client.ConnectionManager$HConnectionImplementation
//        org.apache.hadoop.hbase.ipc.RpcControllerFactory
//        hBaseAdmin = new HBaseAdmin(conf);
//        hTable = new HTable(conf, TN);
        hBaseAdmin = connection.getAdmin();
        hTable = connection.getTable(TableName.valueOf(TN));
    }

    public static void end() throws Exception {
        if (hBaseAdmin != null) {
            hBaseAdmin.close();
        }
        if (hTable != null) {
            hTable.close();
        }
    }

    public static void createTable() throws Exception {
        setup();
        HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(TN));

        HColumnDescriptor cf1 = new HColumnDescriptor("cf1");

        cf1.setInMemory(true);
        cf1.setMaxVersions(1);

        desc.addFamily(cf1);

        if(hBaseAdmin.tableExists(TableName.valueOf(TN))) {
            System.out.println("delete--table");
            hBaseAdmin.disableTable(TableName.valueOf(TN));
            hBaseAdmin.deleteTable(TableName.valueOf(TN));
        }
        hBaseAdmin.createTable(desc);
        end();
    }

    public void putHdfsxml(Configuration conf) {
        conf.set("dfs.nameservices", "mycluster");
        conf.set("dfs.ha.namenodes.mycluster", "nn1,nn2");
        conf.set("dfs.namenode.rpc-address.mycluster.nn1", "node1:8020");
        conf.set("dfs.namenode.rpc-address.mycluster.nn2", "node2:8020");
        conf.set("dfs.namenode.http-address.mycluster.nn1", "node1:50070");
        conf.set("dfs.namenode.http-address.mycluster.nn2", "node2:50070");
        conf.set("dfs.namenode.shared.edits.dir", "qjournal://node2:8485;node3:8485;node4:8485/mycluster");
        conf.set("dfs.journalnode.edits.dir", "/opt/journal/data");
        conf.set("dfs.client.failover.proxy.provider.mycluster", "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
    }

    @Test
    public void insertDB() throws Exception {
        String pNum = getPhone("186");

        // rowkey: 手机号+时间
        Put put = new Put((pNum + "_" + getDate("2016")).getBytes());

        put.add("cf1".getBytes(), "phonenum".getBytes(), pNum.getBytes());
        // tnum 对方手机号
        put.add("cf1".getBytes(), "tnum".getBytes(), getPhone("177").getBytes());
        // type 主叫或者被叫 1 0
        put.add("cf1".getBytes(), "type".getBytes(), "1".getBytes());


        hTable.put(put);
    }

    private Random r = new Random();

    /**
     * 随机生成手机号码
     *
     * @param prefix
     *            前缀 eq:186
     * @return 手机号码 eq：18612341234
     */
    public String getPhone(String prefix) {
        return prefix + String.format("%08d", r.nextInt(99999999));
    }

    /**
     * 随机生成日期
     *
     * @param year
     *            年份
     * @return 格式:yyyyMMddHHmmss eq：20161227152401
     */
    public String getDate(String year) {
        return year
                + String.format(
                "%02d%02d%02d%02d%02d",
                new Object[] { r.nextInt(12) + 1, r.nextInt(30),
                        r.nextInt(24), r.nextInt(60), r.nextInt(60) });
    }
}
