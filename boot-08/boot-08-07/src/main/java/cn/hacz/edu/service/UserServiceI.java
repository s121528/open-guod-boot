package cn.hacz.edu.service;

import cn.hacz.edu.entity.UserEntity;
import cn.hacz.edu.vo.UserReqVo;
import cn.hacz.edu.vo.UserResVo;

import java.util.List;
import java.util.Map;

public interface UserServiceI {

    int insert(UserEntity userEntity);

    int insertSelective(UserEntity userEntity);

    int insertList(List<UserEntity> userEntitys);

    int updateByPrimaryKeySelective(UserEntity userEntity);

    UserResVo selectUserParam(UserReqVo userReqVo);

    UserResVo selectUserMap(Map parameter);

    UserResVo selectUser(UserReqVo userReqVo);

    List<UserResVo> selectUserObj(Map parameter);
}
