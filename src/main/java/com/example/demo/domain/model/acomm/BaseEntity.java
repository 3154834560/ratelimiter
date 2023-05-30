package com.example.demo.domain.model.acomm;

import com.example.demo.infrastructure.tool.IdTool;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 王景阳
 * @date 2023-03-27 15:52
 */
@Getter
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public BaseEntity() {
        this.id = IdTool.generateIdOfLong();
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.version = 1L;
        this.deleteFlag = 0;
    }

    /**
     * id
     */
    private final Long id;
    /**
     * 用于实现乐观锁版本号（用于乐观锁，默认为 1 ）
     */
    private final Long version;
    /**
     * 逻辑删除（ 0 未删除，1 删除 ）
     */
    private final Integer deleteFlag;
    /**
     * 创建时间
     */
    private final LocalDateTime createAt;
    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    public void updateSaveTime() {
        this.updateAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseEntity)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        // 两个id为null的entity会被认为是同一个实体
        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : System.identityHashCode(this);
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", version=" + version +
                ", deleteFlag=" + deleteFlag +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
