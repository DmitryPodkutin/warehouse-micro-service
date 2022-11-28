package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.AbstractServiceTest;
import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import com.gmail.podkutin.dmitry.service.AbstractService;
import com.gmail.podkutin.dmitry.test_data.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractCommonServiceTest<E extends AbstractBaseEntity, R extends JpaRepository<E, Integer>,
        S extends AbstractService<E, R >, U extends TestData<E>> extends AbstractServiceTest {

    private final S service;

    private final U  testData;

    public AbstractCommonServiceTest(S service, U testData) {
        this.service=service;
        this.testData = testData;
    }

    @Test
    void save() {
        E expected = testData.getNew();
        service.create(expected);
        E actual = service.get(expected.getId());
        Assertions.assertEquals(expected, actual);
        E entity = testData.getNew();
        entity.setId(NOT_FOUND_ID);
        assertNull(service.create(entity));
        assertThrows(NullPointerException.class,
                () -> service.create(null));
    }

    @Test
    void update() {
        E expected = testData.getUpdated();
        service.update(expected);
        Assertions.assertEquals(expected, service.get(expected.getId()));
        assertThrows(NotFoundException.class,
                () -> service.update(testData.getNew()));
        assertThrows(NullPointerException.class,
                () -> service.update(null));
    }

    @Test
    void delete() {
        E expected = testData.getNew();
        service.create(expected);
        service.delete(expected.getId());
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND_ID));
    }

    @Test
    void get() {
        E expected = service.get(testData.getFirsEntity().getId());
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(expected, service.get(expected.getId()));
    }

    @Test
    void getNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> service.get(NOT_FOUND_ID));
    }

    @Test
    void getAll() {
        List<E> expected = testData.getAll();
        List<E> actual = service.getAll();
        Assertions.assertEquals(expected, actual);
    }
}
