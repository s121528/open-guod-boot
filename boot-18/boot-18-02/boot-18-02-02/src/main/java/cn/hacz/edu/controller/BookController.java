package cn.hacz.edu.controller;

import cn.hacz.edu.constant.Constant;
import cn.hacz.edu.dto.BookDto;
import cn.hacz.edu.vo.ApiResult;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 获取ES信息
     *
     * @return
     * @throws IOException
     */
    @GetMapping("/es")
    public ApiResult getEsInfo() throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // SearchRequest
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(Constant.INDEX);
        searchRequest.source(searchSourceBuilder);
        // 查询ES
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return ApiResult.ok(searchResponse);
    }

    /**
     * 列表查询
     *
     * @param page
     * @param rows
     * @param keyword
     * @return
     * @throws IOException
     */
    @GetMapping("/book")
    public ApiResult list(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "10") Integer rows,
                          String keyword) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页采用简单的from + size分页，适用数据量小的，了解更多分页方式可自行查阅资料
        searchSourceBuilder.from((page - 1) * rows);
        searchSourceBuilder.size(rows);
        // 查询条件，只有查询关键字不为空才带查询条件
        if (StringUtils.isNoneBlank(keyword)) {
            QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(keyword, "name", "desc");
            searchSourceBuilder.query(queryBuilder);
        }
        // 排序，根据ID倒叙
        searchSourceBuilder.sort("id", SortOrder.DESC);
        // SearchRequest
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.source(searchSourceBuilder);
        // 查询ES
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = searchResponse.getHits();
        // 获取总数
        Long total = hits.getTotalHits().value;
        // 遍历封装列表对象
        List<BookDto> bookDtoList = new ArrayList<>();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit searchHit : searchHits) {
            bookDtoList.add(JSONUtil.toBean(searchHit.getSourceAsString(), BookDto.class));
        }
        return ApiResult.ok(bookDtoList).put("total", total);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/book/{id}")
    public ApiResult getById(@PathVariable("id") String id) throws IOException {
        // GetRequest
        GetRequest getRequest = new GetRequest(Constant.INDEX, id);
        // 查询ES
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        BookDto bookDto = JSONUtil.toBean(getResponse.getSourceAsString(), BookDto.class);
        return ApiResult.ok(bookDto);
    }


    /**
     * 添加文档
     *
     * @param bookDto
     * @return
     * @throws IOException
     */
    @PostMapping("/book")
    public ApiResult add(@RequestBody BookDto bookDto) throws IOException {
        // IndexRequest
        IndexRequest indexRequest = new IndexRequest(Constant.INDEX);
        Long id = System.currentTimeMillis();
        bookDto.setId(id);
        String source = JSONUtil.toJsonStr(bookDto);
        indexRequest.id(id.toString()).source(source, XContentType.JSON);
        // 操作ES
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        return ApiResult.ok(indexResponse);
    }

    /**
     * 修改文档
     *
     * @param bookDto
     * @return
     * @throws IOException
     */
    @PutMapping("/book")
    public ApiResult update(@RequestBody BookDto bookDto) throws IOException {
        // UpdateRequest
        UpdateRequest updateRequest = new UpdateRequest(Constant.INDEX, bookDto.getId().toString());
        updateRequest.doc(JSONUtil.toJsonStr(bookDto), XContentType.JSON);
        // 操作ES
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        return ApiResult.ok(updateResponse);
    }

    /**
     * 删除文档
     *
     * @param id
     * @return
     * @throws IOException
     */
    @DeleteMapping("/book/{id}")
    public ApiResult deleteById(@PathVariable("id") String id) throws IOException {
        // DeleteRequest
        DeleteRequest deleteRequest = new DeleteRequest(Constant.INDEX, id);
        // 操作ES
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        return ApiResult.ok(deleteResponse);
    }
}
