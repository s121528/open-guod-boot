package cn.hacz.edu.controller;

import java.io.IOException;
import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;

import cn.hacz.edu.vo.ApiResult;
import cn.hacz.edu.vo.UserInfoRes;
import cn.hutool.json.JSONUtil;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guodd
 * @since 1.0
 */
@RestController
public class UserController {
    @Autowired
    private RestClient restClient;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @GetMapping(value = "/getUserAll")
    public void getUserAll() throws IOException {
        Request request = new Request("GET", "/world/_search");
        String str = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"email\": \"@qq.com\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
        HttpEntity entity = new NStringEntity(str, ContentType.APPLICATION_JSON);
        request.setEntity(entity);
        Response response = restClient.performRequest(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @GetMapping(value = "/test")
    public ApiResult test() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("test_search_index");

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQuery01 = QueryBuilders.termQuery("username", "username_1753");
        TermQueryBuilder termQuery02 = QueryBuilders.termQuery("email", "1753@qq.com");
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery().must(termQuery01).must(termQuery02);

        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = searchResponse.getHits();
        System.out.println(hits.getTotalHits().value);
        List<UserInfoRes> users = new ArrayList<>();
        hits.forEach(e -> {
                    UserInfoRes userInfoRes = JSONUtil.toBean(e.getSourceAsString(), UserInfoRes.class);
                    users.add(userInfoRes);
                }
        );
        return ApiResult.ok(users).put("total", hits.getTotalHits());
    }
}
