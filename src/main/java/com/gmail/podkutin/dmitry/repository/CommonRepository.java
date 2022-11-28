package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractBaseEntity> extends JpaRepository<E, Integer> {
}
