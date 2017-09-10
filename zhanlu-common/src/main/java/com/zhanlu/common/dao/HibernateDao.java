package com.zhanlu.common.dao;

import com.zhanlu.common.entity.IdEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Dao层的通用父类
 *
 * @param <T>  实体对象
 * @param <PK> 实体对象ID
 */
@Slf4j
@Repository
public class HibernateDao<T extends IdEntity, PK extends Serializable> extends AbstractDao<T, PK> {

}
