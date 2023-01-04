package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface CommonService<E extends AbstractBaseEntity> {

    E create(@Valid @NotNull E entity);

    E update(@Valid @NotNull E entity);

    void delete(@Min(1) int id);

    E get(@Min(1) int id);

    List<E> getAll();
}