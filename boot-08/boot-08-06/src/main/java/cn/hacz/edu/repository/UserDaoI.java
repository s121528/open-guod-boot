package cn.hacz.edu.repository;

import cn.hacz.edu.entity.UserEntity;
import cn.hacz.edu.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * project - GitHub整理
 *
 * @author guod
 * @version 1.0
 */
@Repository
public interface UserDaoI extends BaseRepository<UserEntity, Integer> {
    /**
     * 功能描述：根据id查询
     *
     * @param id
     * @return
     */
    UserEntity findById(Integer id);

}
