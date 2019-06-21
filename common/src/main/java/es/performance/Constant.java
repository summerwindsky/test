package es.performance;

/**
 * 从配置文件中读取配的的类，包含了配置文件的配置项
 * Title: Constants
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhn
 * @version 1.0
 * @date 2017-4-26 下午1:34:39
 *
 */
public interface Constant {
//    es.cluster=HYYD_5_6
//    es.ip=172.16.124.5
//    es.port=9300
//    es.scroll.timeout=1800000
//    es.scroll.size=20
//    es.index=zk_all
//    es.type=case
//    es.writAnalyzer.type=writAnalyzer
//    es.relation.index=relation
//    es.judge.type=judge
    public static final String es_cluster_name = "HYYD_5_6";

    public static String es_cluster_ip = "172.16.124.5";

    public static int es_cluster_port = 9300;

    public static long es_scroll_timeout = 1800000;

    public static int es_scroll_size = 20;

    public static String es_index = "zk_all";

    public static String es_case_type = "case";

    public static String es_writAnalyzer_type = "writAnalyzer";

//    es.index.lawyer=zklawyer
//    es.index.lawyer.type.writAnalyzer=writAnalyzer
//    es.index.lawyer.type.lawyer=lawyer
//    es.index.lawyer.type.ls=ls
    //律师律所
    public static String es_index_lawyer = "zklawyer";

    public static String es_type_writAnalyzer = "writAnalyzer";

    public static String es_type_lawyer = "lawyer";

    public static String es_type_ls = "ls";


}
