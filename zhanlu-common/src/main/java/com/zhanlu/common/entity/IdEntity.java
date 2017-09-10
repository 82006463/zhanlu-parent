package com.zhanlu.common.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * 通用的实体类
 *
 * @author zhanlu
 * @date 2017-03-11
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable {

    private static final long serialVersionUID = -5288872906807628038L;

    //主键标识ID
    protected Long id;

    //状态：0:删除, 1:正常
    private Integer status;

    /**
     * 安全实体的主键生成策略为序列，序列名称为SEC_SEQUENCE
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
