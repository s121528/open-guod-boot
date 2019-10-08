package cn.hacz.edu.util;

import cn.hacz.edu.entity.FieldMapping;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author guodd
 * @since 1.0
 */
@Slf4j
public class ElasticSearchUtils {

    private static List<FieldMapping> getFieldInfo(Class clazz) {
        return getFieldInfo(clazz, null);
    }

    private static List<FieldMapping> getFieldInfo(Class clazz, String fieldName) {
        Field[] fields = clazz.getDeclaredFields();
        List<FieldMapping> fieldMappingList = new ArrayList<>();
        for (Field field : fields) {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            if (fieldInfo == null) {
                continue;
            }
            if ("object".equals(fieldInfo.type())) {
                Class fc = field.getType();
                if (fc.isPrimitive()) { //如果是基本数据类型
                    String name = field.getName();
                    if (StringUtils.isNotBlank(fieldName)) {
                        name = name + "." + fieldName;
                    }
                    fieldMappingList.add(new FieldMapping(name, fieldInfo.type(), fieldInfo.participle(), fieldInfo.ignoreAbove()));
                } else {
                    // 判断是否为List
                    if (fc.isAssignableFrom(List.class)) {
                        System.out.println("List类型：" + field.getName());
                        //得到泛型类型
                        Type gt = field.getGenericType();
                        ParameterizedType pt = (ParameterizedType) gt;
                        Class lll = (Class) pt.getActualTypeArguments()[0];
                        fieldMappingList.addAll(getFieldInfo(lll, field.getName()));
                    } else {
                        fieldMappingList.addAll(getFieldInfo(fc, field.getName()));
                    }
                }
            } else {
                String name = field.getName();
                if (StringUtils.isNotBlank(fieldName)) {
                    name = fieldName + "." + name;
                }
                fieldMappingList.add(new FieldMapping(name, fieldInfo.type(), fieldInfo.participle(), fieldInfo.ignoreAbove()));
            }
        }
        return fieldMappingList;
    }

    /**
     * 创建mapping
     *
     * @param index  索引
     * @param type   类型
     * @param clazz  索引类型
     * @param client es客户端
     */
    public static boolean createIndexAndCreateMapping(String index, String type, Class clazz, RestHighLevelClient client, boolean dropOldIndex, int number_of_shards, int number_of_replicas) {
        /*if (isIndexExists(index, client)) {
            if (dropOldIndex) {
                AcknowledgedResponse acknowledgedResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
                if (acknowledgedResponse.isAcknowledged()) {
                    return createIndexAndCreateMapping(index, type, getFieldInfo(clazz), client, number_of_shards, number_of_replicas);
                } else {
                    log.error("删除旧索引数据失败，创建索引mapping失败");
                    return false;
                }
            }
            log.info("不需要删除旧的索引，没有进一步创建索引结构哦~");
            return true;
        } else {

        }*/
        return createIndexAndCreateMapping(index, type, getFieldInfo(clazz), client, number_of_shards, number_of_replicas);
    }

    /**
     * 判断索引是否存在 传入参数为索引库名称
     *
     * @param indexName
     * @param client
     * @return
     */
    public static boolean isIndexExists(String indexName, TransportClient client) {
        IndicesExistsRequest inExistsRequest = new IndicesExistsRequest(indexName);
        IndicesExistsResponse inExistsResponse = client.admin().indices()
                .exists(inExistsRequest).actionGet();
        if (inExistsResponse.isExists()) {
            return true;
        }
        return false;
    }

    /**
     * 根据信息自动创建索引与mapping
     * 构建mapping描述
     *
     * @param index              索引名称
     * @param type               类型名称
     * @param fieldMappingList   字段信息
     * @param client             es客户端
     * @param number_of_shards   分片数
     * @param number_of_replicas 副本数
     * @return
     */
    public static boolean createIndexAndCreateMapping(String index, String type, List<FieldMapping> fieldMappingList, RestHighLevelClient client, int number_of_shards, int number_of_replicas) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        HashMap<String, Object> settings_map = new HashMap<>();
        settings_map.put("number_of_shards", number_of_shards);
        settings_map.put("number_of_replicas", number_of_replicas);
        XContentBuilder mapping;
        try {
            // 设置之定义字段
            mapping = XContentFactory.jsonBuilder()
                    .startObject()
                    .startObject("properties");
            for (FieldMapping info : fieldMappingList) {
                String field = info.getField();
                String dateType = info.getType();
                if (dateType == null || "".equals(dateType.trim())) {
                    dateType = "string";
                }
                dateType = dateType.toLowerCase();
                int participle = info.getParticiple();
                if ("string".equals(dateType)) {
                    if (participle == 0) {
                        mapping.startObject(field)
                                .field("type", "keyword")
                                .field("index", false)
                                .field("ignore_above", info.getIgnoreAbove())
                                .endObject();
                    } else if (participle == 1) {
                        mapping.startObject(field)
                                .field("type", "text")
                                .field("analyzer", "ik_smart")
                                .endObject();
                    } else if (participle == 2) {
                        mapping.startObject(field)
                                .field("type", "text")
                                .field("analyzer", "ik_max_word")
                                .endObject();
                    }
                } else if ("datetime".equals(dateType)) {
                    mapping.startObject(field)
                            .field("type", "date")
                            .field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
                            .endObject();
                } else if ("timestamp".equals(dateType)) {
                    mapping.startObject(field)
                            .field("type", "date")
                            .field("format", "strict_date_optional_time||epoch_millis")
                            .endObject();
                } else if ("float".equals(dateType) || "double".equals(dateType)) {
                    mapping.startObject(field)
                            .field("type", "scaled_float")
                            .field("scaling_factor", 100)
                            .endObject();
                } else {
                    mapping.startObject(field)
                            .field("type", dateType)
                            .field("index", true)
                            .endObject();
                }
            }
            mapping.endObject()
                    .endObject();
            System.out.println(JSONUtil.toJsonStr(mapping));
            createIndexRequest.source(mapping).source(settings_map);
            client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            // client.setSettings(settings_map).addMapping(type, mapping).execute().actionGet();
            return true;
        } catch (IOException e) {
            log.error("根据信息自动创建索引与mapping创建失败，失败信息为:{}", e.getMessage());
            return false;
        }
    }
}