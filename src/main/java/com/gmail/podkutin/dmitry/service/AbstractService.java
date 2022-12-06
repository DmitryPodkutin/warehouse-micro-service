package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.gmail.podkutin.dmitry.util.ValidationUtil.checkNotFoundWithId;

public abstract class AbstractService<E extends AbstractBaseEntity, R extends JpaRepository<E,Integer>>
        implements CommonService<E> {

    private final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public E create(E entity) {
        Objects.requireNonNull(entity, "entity must not be null");
        if (!entity.isNew()) {
            return null;
        }
        return repository.save(entity);
    }

    @Transactional
    @Override
    public E update(E entity) {
        Objects.requireNonNull(entity, "entity must not be null");
        int id = Optional.ofNullable(entity.getId()).orElse(0);
        checkNotFoundWithId(get(id) != null, id);
        return repository.save(entity);
    }

    @Transactional
    @Override
    public void delete(int id) {
        repository.delete(checkNotFoundWithId(repository.findById(id), id));
    }

    @Override
    public E get(int id) {
        return checkNotFoundWithId(repository.findById(id), id);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }
}