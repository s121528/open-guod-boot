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

    // 方法1：顺序传参法，这种方法不建议使用，sql层表达不直观，且一旦顺序调整容易出错。
    // 由于是多参数那么就不能使用parameterType，改用#｛index｝是第几个就用第几个的索引，索引从0开始
    UserResVo selectUser(String name, Integer age);

    // 方法2：@Param注解传参法，这种方法在参数不多的情况还是比较直观的，推荐使用。
    // 由于是多参数那么就不能使用parameterType， 这里用@Param来指定哪一个
    UserResVo selectUserParam(@Param("name") String name, @Param("age") Integer age);

    // 方法3：Map传参法，这种方法适合传递多个参数，且参数易变能灵活传递的情况。
    UserResVo selectUserMap(Map<String, Object> params);

    // 方法4：Java Bean传参法，这种方法很直观，但需要建一个实体类，扩展不容易，需要加属性，看情况使用。
    List<UserResVo> selectUserObj(Map<String, Object> params);
}
