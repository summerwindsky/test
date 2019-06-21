package es.performance;

import org.apache.commons.collections.MapUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Title:
 * Description:
 * Company: 北京华宇元典信息服务有限公司
 *
 * @author zhangjing
 * @version 1.0
 * @date 2018-09-04 11:09
 */
public class QueryTest {

        private static TermQueryBuilder queryBuilder = QueryBuilders.termQuery("AJLB", "刑事案件");

    public static void main(String[] args) {
//        queryCount();

        long start = System.currentTimeMillis();
        shardQuery();
        System.out.println("==" + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        gloabQuery();
        System.out.println("===" + (System.currentTimeMillis() - start));


    }



    public static void gloabQuery() {
        ExecutorService scanLawsExec = Executors.newFixedThreadPool((int)8);
        CompletionService<String> scanLawsEcs = new ExecutorCompletionService<String>(scanLawsExec);
        for (int i =0; i< 8; i++) {
            scanLawsEcs.submit((Callable<String>) () -> {
                getSearchResponse(null, queryBuilder);
                return Thread.currentThread().getName() + "结束！";
            });
        }

        for (int i = 0; i < 8; i++) {
            try {
                String end = scanLawsEcs.take().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void shardQuery() {


        Map<String, DiscoveryNode> shards = ElasticUtil.getElasticReader().getShards(Constant.es_index_lawyer, Constant.es_writAnalyzer_type);

        ExecutorService scanLawsExec = Executors.newFixedThreadPool((int)8);
        CompletionService<String> scanLawsEcs = new ExecutorCompletionService<String>(scanLawsExec);
        for (Map.Entry<String, DiscoveryNode> entry : shards.entrySet()) {
            String shardnum = entry.getKey();
            scanLawsEcs.submit((Callable<String>) () -> {
                getSearchResponse(shardnum, queryBuilder);
                return Thread.currentThread().getName() + "结束！";
            });
        }

        for (int i = 0; i < 8; i++) {
            try {
                String end = scanLawsEcs.take().get();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void getSearchResponse(String shardId, QueryBuilder queryBuilder) {
        String[] rFields = new String[]{"LAWYER", "XZLS"};
        SearchResponse searchResponse = ElasticUtil.getResponse(Constant.es_index_lawyer, Constant.es_type_writAnalyzer, queryBuilder,
                rFields, shardId);
        String writId = null;
        while (true) {
            try {
                SearchHits results = searchResponse.getHits();
                if (results.getHits().length <= 0) {
                    break;
                }
                for (SearchHit hit : results.getHits()) {
                    writId = hit.getId();
                    // 1. 获取律所数据
                    Map<String, Object> sourceMap = hit.getSourceAsMap();
                    if (MapUtils.isNotEmpty(sourceMap)) {
                        sourceMap.put("_id", writId);
                    }

                }
                String scrollId = searchResponse.getScrollId();
                searchResponse = ElasticUtil.getElasticReader().searchScroll(scrollId, Constant.es_scroll_timeout);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



}
