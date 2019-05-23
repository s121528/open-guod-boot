package cn.hacz.edu;

import cn.hacz.edu.entity.UserEntity;
import cn.hacz.edu.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * project - GitHub整理
 *
 * @author dong
 * @version 3.0
 * @date 日期:2018/7/8 时间:13:39
 * @JDK 1.8
 * @Description 功能模块：客户端
 */
@RestController
@Slf4j
public class UserClientController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping(value = "/getClientUserVo")
    public Object getUserVo(UserVo userVo) {
        // 必须使用LinkedMultiValueMap传递，不能使用对象，如果使用对象传输，可以进入controller中但值为空
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", userVo.getId());
        map.add("name", "guodd");
        map.add("age", "12");
        String postForObject = restTemplate.postForObject("http://127.0.0.1:10601/getServiceUserVo", map, String.class);
        log.info(postForObject);
        return postForObject;
    }

    @GetMapping(value = "/getClientUserJson")
    public Object getUserJson(@RequestBody UserVo userVo) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userVo.getName());
        userEntity.setAge(12);
        String postForObject = restTemplate.postForObject("http://127.0.0.1:10601/getServiceUserJson", userEntity, String.class);
        log.info(postForObject);
        return postForObject;
    }

    @GetMapping(value = "/getClientUserMap")
    public Object getUserMap(@RequestParam Map<String, Object> map) {
        MultiValueMap<String, String> Parameter = new LinkedMultiValueMap<>();
        Parameter.add("id", "12");
        Parameter.add("name", "guodd");
        String postForObject = restTemplate.postForObject("http://127.0.0.1:10601/getServiceUserMap", Parameter, String.class);
        log.info(postForObject);
        return postForObject;
    }

    @GetMapping(value = "/getClientUserMapJson")
    public Object getUserMapJson(@RequestBody Map<String, Object> map) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId("66");
        userEntity.setName("guodd");
        userEntity.setAge(12);
        String postForObject = restTemplate.postForObject("http://127.0.0.1:10601/getServiceUserMapJson", userEntity, String.class);
        log.info(postForObject);
        return postForObject;
    }

    @GetMapping(value = "/getClientUserPathVariable/{id}")
    public String getUserPathVariable(@PathVariable String id) {
        String forObject = restTemplate.getForObject("http://127.0.0.1:10601/geServicetUserPathVariable/{id}", String.class, id);
        log.info(forObject);
        return forObject;
    }

    @GetMapping(value = "/getClientUserRequestParam")
    public Object getUserRequestParam(@RequestParam String id) {
        MultiValueMap<String, String> Parameter = new LinkedMultiValueMap<>();
        Parameter.add("id", "12");
        Parameter.add("name", "guodd");
        String postForObject = restTemplate.postForObject("http://127.0.0.1:10601/getServiceUserRequestParam", Parameter, String.class);
        log.info(postForObject);
        return postForObject;
    }

    @GetMapping(value = "/getClientParam")
    public UserEntity getParam(String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        return userEntity;
    }
}