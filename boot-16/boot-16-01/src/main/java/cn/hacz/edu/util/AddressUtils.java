package cn.hacz.edu.util;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 获取IP地址的位置
 *
 * @author guodd
 * @version 1.0
 */
@Slf4j
public class AddressUtils {

    /**
     * 根据IP获取位置信息
     *
     * @param ip 127.0.0.1
     * @return 省市
     */
    public static String getRealAddressByIP(String ip) {
        String address = "";
        RestTemplate template = new RestTemplate();
        template.getMessageConverters().clear();
        template.getMessageConverters().add(new FastJsonHttpMessageConverter());
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("ak", "killer");
        String forObject = template.getForObject("http://api.map.baidu.com/location/ip?ak=lG0ZZqIeSOYTfaZrHKrlun2HYHvHHvcl&ip=" + ip,
                String.class);
        if (!StringUtils.isEmpty(forObject)) {
            // address = forObject.getContent().getAddress_detail().getProvince() + " " + forObject.getContent().getAddress_detail().getCity();
        }
        return address;
    }
}
