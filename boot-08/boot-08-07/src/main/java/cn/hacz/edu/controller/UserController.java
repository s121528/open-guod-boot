package cn.hacz.edu.controller;

import cn.hacz.edu.entity.UserEntity;
import cn.hacz.edu.service.UserServiceI;
import cn.hacz.edu.vo.UserReqVo;
import cn.hacz.edu.vo.UserResVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * project -
 *
 * @author yanfa07
 * @version 1.0
 * @date 日期:2019/4/25 时间:13:47
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceI userServiceI;

    /**
     * 功能描述：参数
     */
    @PostMapping(value = "/insertSelective")
    public Integer insertSelective(@RequestBody UserReqVo userReqVo) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userReqVo, userEntity);
        return userServiceI.insertSelective(userEntity);
    }

    @PostMapping(value = "/selectUser")
    public UserResVo selectUser(@RequestBody UserReqVo userReqVo) {
        return userServiceI.selectUser(userReqVo);
    }

    @PostMapping(value = "/selectUserParam")
    public UserResVo selectUserParam(@RequestBody UserReqVo userReqVo) {
        return userServiceI.selectUserParam(userReqVo);
    }

    @PostMapping(value = "/selectUserMap")
    public UserResVo selectUserMap(@RequestBody Map parameter) {
        return userServiceI.selectUserMap(parameter);
    }

    @PostMapping(value = "/selectUserObj")
    public List<UserResVo> selectUserObj(@RequestBody Map parameter) {
        return userServiceI.selectUserObj(parameter);
    }
}
