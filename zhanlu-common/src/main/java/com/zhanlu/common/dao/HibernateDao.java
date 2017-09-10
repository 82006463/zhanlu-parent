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
 * @param <T>  实体对象
 * @param <PK> 实体对象ID
 */
@Slf4j
@Repository
public class HibernateDao<T extends IdEntity, PK extends Serializable> extends AbstractDao<T, PK> {

}
