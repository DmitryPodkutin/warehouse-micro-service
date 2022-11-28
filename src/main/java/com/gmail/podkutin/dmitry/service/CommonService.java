package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;

import java.util.List;

public interface CommonService<E extends AbstractBaseEntity> {

    E create(E entity);

    E update(E entity);

    void delete(int id);

    E get(int id);

    List<E> getAll();
}