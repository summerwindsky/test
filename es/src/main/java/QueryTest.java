import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

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

    public static void main(String[] args) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("AJLB", "刑事案件"))
                .must(QueryBuilders.rangeQuery("timestamp").gte(1536055117006L));
        SearchResponse searchResponse = ElasticUtil.getResponse(Constant.es_index_lawyer, Constant.es_type_writAnalyzer, queryBuilder,
                null, null);

        System.out.println(searchResponse.getHits().totalHits);
    }
}
