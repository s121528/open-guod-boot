package cn.hacz.edu.dao;

import cn.hacz.edu.mapping.many2one.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * project - GitHub整理
 *
 * @author guodd
 * @version 1.0
 */
@Repository
public interface GroupDaoI extends JpaRepository<GroupEntity, Serializable> {
}
