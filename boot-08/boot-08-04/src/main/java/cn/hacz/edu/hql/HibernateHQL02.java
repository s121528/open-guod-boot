package cn.hacz.edu.hql;

import cn.hacz.edu.mapping.many2one.GroupEntity;
import com.fasterxml.classmate.AnnotationConfiguration;
import org.hibernate.Session;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * project - GitHub整理
 *
 * @author
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class HibernateHQL02 {
    @PersistenceContext
    private EntityManager entityManager;

    public Session getSession() {
        return this.entityManager.unwrap(Session.class);
    }

    /**
     * is not empty & is empty
     */
    public void testHQL_20() {
        List<GroupEntity> list = this.getSession().createQuery("from GroupEntity g where g.users is not empty", GroupEntity.class).list();
        list.forEach(System.out::println);
    }


    /**
     * like查询（_name、%name、%name%）
     */
    public void testHQL_21() {
        List<GroupEntity> list = this.getSession().createQuery("from GroupEntity g where g.name like '%5'", GroupEntity.class).list();
        list.forEach(System.out::println);
    }

    public void testHQL_22() {
        Query query = this.getSession().createQuery("select " +
                "lower(g.name) ," +
                "upper(g.name) ," +
                "trim(g.name)," +
                "concat(g.name, '***')," +
                "length(g.name)" +
                "from GroupEntity g");
        List list = query.list();
    }

    public void testHQL_23() {
        Query query = this.getSession().createQuery("select current_date, current_time, current_timestamp, t.id from Topic t");
        List list = query.list();
        for (Object o : list) {
            Object[] arr = (Object[]) o;
            System.out.println(arr[0] + " | " + arr[1] + " | " + arr[2] + " | " + arr[3]);
        }
    }

    /**
     * in查询，特别注意：in里面是数组对象，防止直接使用字符串
     */
    public void testHQL_24() {
        Query<GroupEntity> query = this.getSession().createQuery(" from G GroupEntity WHERE G.ID IN (:ids) ", GroupEntity.class);
        Integer[] ids = new Integer[]{};
        query.setParameter("ids", ids);
        // query.setParameter("ids", "1, 2, 3, 4");错误的写法
        List<GroupEntity> list = query.list();
        for (int i = 0; i < list.size(); i++) {
        }
    }
}
