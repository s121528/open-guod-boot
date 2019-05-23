package cn.hacz.edu.controller;

import cn.hacz.edu.entity.StudentEntity;
import cn.hacz.edu.service.UserServiceI;
import cn.hacz.edu.dao.StudentDaoI;
import cn.hacz.edu.dao.UserDaoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * project - GitHub整理
 *
 * @author guod
 * @version 1.0
 * @date 日期:2018/7/6 时间:15:07
 * @JDK 1.8
 * @Description 功能模块：
 */
@RestController
public class UserController {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private UserDaoI userDaoI;
    @Autowired
    private StudentDaoI studentDaoI;

    @PostMapping(value = "/init")
    public void getUser() {
        List<StudentEntity> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setFirstName("firstName" + i);
            studentEntity.setLastName("lastName" + i);
            studentEntity.setSex("sex" + i);
            studentEntity.setEmail("");
            studentEntity.setAge(0);
            studentEntity.setId(0);
            list.add(studentEntity);
        }
        studentDaoI.saveAll(list);
    }

    /**
     * 功能描述：获取信息
     */
    @PostMapping(value = "/getInfo")
    public Page<StudentEntity> getInfo(@RequestParam Map paramer) {
        // 特别注意：page是从0开始的，（page-1）*rows，rows/page*rows，rows
        Page<StudentEntity> all = studentDaoI.findAll(PageRequest.of(2, 10));
        Page<StudentEntity> studentEntities = studentDaoI.queryByParams("firstName1", "lastName1", PageRequest.of(3, 10));
        return all;
    }
}
