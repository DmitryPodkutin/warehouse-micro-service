package com.gmail.podkutin.dmitry.util;

import org.springframework.util.Assert;

public interface HasId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return getId() == null;
    }

    // doesn't work for hibernate lazy proxy
   public default int hasId() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
