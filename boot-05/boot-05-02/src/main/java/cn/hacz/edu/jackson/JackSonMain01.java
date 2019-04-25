package cn.hacz.edu.jackson;

import cn.hacz.edu.modules.system.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

/**
 * project - GitHub整理 ETC发票系统
 *
 * @author dong
 * @version 3.0
 * @date 日期:2018/7/8 时间:11:14
 * @JDK 1.8
 * @Description 功能模块：
 */
public class JackSonMain01 {
    private ObjectMapper objectMapper = new ObjectMapper();
    private UserEntity userEntity = new UserEntity("guo", 22);

    @Test
    public void jackSonMainTest01() throws Exception {
        // 序列化
        String s = objectMapper.writeValueAsString(userEntity);
        System.out.println(s);
        // 反序列化（两种方式）
        UserEntity userEntity = objectMapper.readValue(s, UserEntity.class);
        System.out.println(userEntity.getName());
        // 自定义JSON节点
        ObjectNode objectNode01 = objectMapper.createObjectNode();
        objectNode01.put("aa", "1");
        ObjectNode objectNode02 = JsonNodeFactory.instance.objectNode();
        objectNode02.put("aa", "1");
        ArrayNode arrayNode01 = objectMapper.createArrayNode();
        ArrayNode arrayNode02 = JsonNodeFactory.instance.arrayNode();
        System.out.println(objectMapper.writeValueAsString(objectNode01));
        arrayNode01.add(objectNode01);
        arrayNode01.add(objectNode02);
        arrayNode02.add(objectNode01);
        System.out.println(objectMapper.writeValueAsString(arrayNode01));
    }
}
