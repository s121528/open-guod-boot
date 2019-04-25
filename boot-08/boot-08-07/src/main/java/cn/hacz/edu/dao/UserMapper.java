package cn.hacz.edu.dao;

import cn.hacz.edu.entity.UserEntity;
import cn.hacz.edu.vo.UserResVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    int insert(@Param("userEntity") UserEntity userEntity);

    int insertSelective(@Param("userEntity") UserEntity userEntity);

    int insertList(@Param("userEntitys") List<UserEntity> userEntitys);

    int updateByPrimaryKeySelective(@Param("userEntity") UserEntity userEntity);

    // 方法1：顺序传参法
    UserResVo selectUser(String name, Integer age);

    // 方法2：@Param注解传参法
    UserResVo selectUserParam(@Param("name") String name, @Param("age") Integer age);

    // 方法3：Map传参法
    UserResVo selectUserMap(Map<String, Object> params);

    // 方法4：Java Bean传参法
    List<UserResVo> selectUserObj(Map<String, Object> params);
}
