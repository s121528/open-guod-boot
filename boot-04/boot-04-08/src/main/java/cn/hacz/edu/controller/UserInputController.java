package cn.hacz.edu.controller;

import cn.hacz.edu.dto.UserInputReqDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * project -
 *
 * @author guodd
 * @version 1.0
 * @date 日期:2019/5/22 时间:13:53
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
public class UserInputController {
    /**
     * 功能描述：测试
     */
    @PostMapping(value = "/test")
    public Object test(@RequestBody UserInputReqDTO userInputReqDTO) {
        return null;
    }
}
