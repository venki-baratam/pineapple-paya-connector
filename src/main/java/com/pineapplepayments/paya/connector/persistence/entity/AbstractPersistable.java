package com.pineapplepayments.paya.connector.persistence.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractPersistable<PK extends Serializable> implements Serializable {

    private static final long serialVersionUID = 2951033292944758503L;

    @Version
    private Long version;

    public abstract PK getId();

    protected abstract void setId(PK id);

    public Long getVersion() {
        return version;
    }

    public boolean isNew() {
        return null == getId();
    }

    @Override
    public String toString() {
        return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
    }
}
