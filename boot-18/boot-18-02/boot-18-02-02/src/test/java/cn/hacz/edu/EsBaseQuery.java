package cn.hacz.edu;

import cn.hacz.edu.entity.FieldMapping;
import cn.hacz.edu.entity.GwAdvice;
import cn.hacz.edu.util.ElasticSearchUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;

/**
 * @author guodd
 * @since 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsBaseQuery {
    @Autowired
    private RestClient restClient;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建文档（指定id和不指定两种形式）
     * put http://211.144.5.80:30136/posts/_doc/1   {"name":"guo","age":22}
     * post http://211.144.5.80:30136/posts/_doc   {"name":"guo","age":22}
     */
    @Test
    public void index() {
        IndexRequest request = new IndexRequest("posts")
                .id("1")
                .source("field", "value")
                .opType(DocWriteRequest.OpType.CREATE);
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("name", "guo");
            map.put("age", 22);
            map.put("date", new Date());
            request.source(map);
            IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (ElasticsearchException e) {
            if (e.status() == RestStatus.CONFLICT) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询文档
     * get http://211.144.5.80:30136/test01/_doc/1
     *
     * @throws IOException
     */
    @Test
    public void getApi() throws IOException {
        GetRequest getRequest = new GetRequest(
                "posts",
                "1");
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(exists);
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        DeleteRequest request = new DeleteRequest("posts", "1");
        DeleteResponse deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(getResponse);
    }

    @Test
    public void info() throws Exception {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.indices("test");
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(search);
    }
}