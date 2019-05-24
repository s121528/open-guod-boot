package cn.hacz.edu.service.impl;

import cn.hacz.edu.dao.UserMapper;
import cn.hacz.edu.entity.UserEntity;
import cn.hacz.edu.service.UserServiceI;
import cn.hacz.edu.vo.UserReqVo;
import cn.hacz.edu.vo.UserResVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserServiceI {

    @Resource
    private UserMapper userMapper;

    @Override
    public int insert(UserEntity userEntity) {
        return userMapper.insert(userEntity);
    }

    @Override
    public int insertSelective(UserEntity userEntity) {
        return userMapper.insertSelective(userEntity);
    }

    @Override
    public int insertList(List<UserEntity> userEntitys) {
        return userMapper.insertList(userEntitys);
    }

    @Override
    public int updateByPrimaryKeySelective(UserEntity userEntity) {
        return userMapper.updateByPrimaryKeySelective(userEntity);
    }

    @Override
    public UserResVo selectUser(UserReqVo userReqVo) {
        return userMapper.selectUser(userReqVo.getName(), userReqVo.getAge());
    }

    @Override
    public UserResVo selectUserParam(UserReqVo userReqVo) {
        return userMapper.selectUserParam(userReqVo.getName(), userReqVo.getAge());
    }

    @Override
    public UserResVo selectUserMap(Map params) {
        return userMapper.selectUserMap(params);
    }

    @Override
    public List<UserResVo> selectUserObj(Map parameter) {
        return userMapper.selectUserObj(parameter);
    }
}