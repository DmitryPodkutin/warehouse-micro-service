package com.gmail.podkutin.dmitry.test_data;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;

import java.util.List;

public interface TestData<E extends AbstractBaseEntity> {

    E getFirsEntity();

    E getNew();

    E getUpdated();

    List<E> getAll();

}
