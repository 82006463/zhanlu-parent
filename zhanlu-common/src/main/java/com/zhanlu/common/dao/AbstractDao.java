package com.zhanlu.common.dao;

import com.zhanlu.common.entity.IdEntity;
import com.zhanlu.common.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Dao层的通用父类
 *
 * @param <T> 实体对象
 * @param <PK> 实体对象ID
 */
@Slf4j
public class AbstractDao<T extends IdEntity, PK extends Serializable> {

    @Resource(name = "hibernateTemplate")
    protected HibernateTemplate hibernateTemplate;

    /**
     * 新增实体对象
     */
    public T save(T entity) throws SQLException {
        hibernateTemplate.save(entity);
        return entity;
    }

    /**
     * 新增/更新实体对象
     */
    public T saveOrUpdate(T entity) throws SQLException {
        hibernateTemplate.saveOrUpdate(entity);
        return entity;
    }

    /**
     * 删除实体对象
     */
    public boolean delete(T entity) throws SQLException {
        hibernateTemplate.delete(entity);
        return true;
    }

    /**
     * 根据ID查询单个对象
     */
    public T selectOne(PK id) throws SQLException {
        return (T) hibernateTemplate.load(ReflectionUtils.getSuperClassGenricType(getClass()), id);
    }

    /**
     * 根据实体对象属性及值查询单个实体对象
     */
    public T selectOne(Map<String, Object> whereMap) throws SQLException {
        List<T> entities = this.selectListByMap(whereMap);
        return entities == null ? null : entities.get(0);
    }

    /**
     * 根据ID集合查询多个实体对象
     */
    public List<T> selectList(List<PK> ids) throws SQLException {
        List<T> entities = new ArrayList<>(ids.size());
        for (PK id : ids)
            entities.add(selectOne(id));
        return entities;
    }

    /**
     * 根据实体对象属性及值查询多个实体对象
     */
    public List<T> selectList(Map<String, Object> whereMap) throws SQLException {
        return this.selectListByMap(whereMap);
    }

    /**
     * 查询所有对象
     */
    public List<T> selectAll() {
        return this.selectListByMap(null);
    }

    private List<T> selectListByMap(Map<String, Object> whereMap) {
        StringBuilder hql = new StringBuilder("SELECT t FROM " + ReflectionUtils.getSuperClassGenricType(getClass()).getName() + " t WHERE 1=1");
        if (whereMap != null && whereMap.size() > 0) {
            List<Object> params = new ArrayList<>(whereMap.size());
            for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
                hql.append(" t." + entry.getKey() + " = ?");
                params.add(entry.getValue());
            }
            return (List<T>) hibernateTemplate.find(hql.toString(), params.toArray());
        }
        return (List<T>) hibernateTemplate.find(hql.toString());
    }
}
