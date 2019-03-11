package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NewapiTest {
    Admin hBaseAdmin;
    Table hTable;

    // 表名
    String TN = "tbl";

    @Before
    public void setup() throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum", "node1,node2,node3");
        conf.set("hbase.rootdir", "hdfs://mycluster/hbase");
        conf.set("hbase.cluster.distributed", "true");
//        putHdfsxml(conf);
        Connection connection = ConnectionFactory.createConnection(conf);
//        org.apache.hadoop.hbase.client.ConnectionManager$HConnectionImplementation
//        org.apache.hadoop.hbase.ipc.RpcControllerFactory
//        hBaseAdmin = new HBaseAdmin(conf);
//        hTable = new HTable(conf, TN);
        hBaseAdmin = connection.getAdmin();
        hTable = connection.getTable(TableName.valueOf(TN));
    }

    @After
    public void end() throws Exception {
        if (hBaseAdmin != null) {
            hBaseAdmin.close();
        }
        if (hTable != null) {
            hTable.close();
        }
    }

    @Test
    public void createTable() throws Exception {
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
}
