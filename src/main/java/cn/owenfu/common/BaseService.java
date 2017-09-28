package cn.owenfu.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Owen
 * Date: 2017/9/17
 * Time: 17:01
 * Just Do It!
 * BaseService description:
 * service层公共类
 */
public abstract class BaseService<T extends BaseDao<E, Long>, E extends BaseEntity<E>> {

    protected Logger logger = LogManager.getLogger(getClass());
    /**
     * 持久层对象
     */
    @Autowired
    protected T dao;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public E get(Long id) {
        return dao.getOne(id);
    }

    /**
     * 获取所有数据
     *
     * @return
     */
    public List<E> findAll() {
        return dao.findAll();
    }

    /**
     * 创建or更新数据
     * @param entity
     * @return
     */
    public E save(E entity) {
        return dao.save(entity);
    }


    public List<E> findAll(Sort var1) {
        return dao.findAll(var1);
    }

    public long count() {
        return dao.count();
    }

    public void delete(Long var1) {
        dao.delete(var1);
    }

    public void delete(E var1) {
        dao.delete(var1);
    }

    public void deleteAll() {
        dao.deleteAll();
    }


}
