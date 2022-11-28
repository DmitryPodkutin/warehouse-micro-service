package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.AbstractServiceTest;
import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import com.gmail.podkutin.dmitry.test_data.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractCommonRepositoryTest<E extends AbstractBaseEntity,
        R extends JpaRepository<E, Integer>, U extends TestData<E>> extends AbstractServiceTest {

    private final R repository;

    private final U testData;

    public AbstractCommonRepositoryTest(R repository, U testData) {
        this.repository = repository;
        this.testData = testData;
    }

    @Test
    @Transactional
    void save() {
        E expected = testData.getNew();
        repository.save(expected);
        E actual = repository.findById(expected.getId()).orElseThrow();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void get() {
        E expected = repository
                .findById(testData.getFirsEntity().getId()).orElseThrow();
        Assertions.assertNotNull(expected);
        Assertions.assertEquals(expected, repository.findById(expected.getId()).orElseThrow());
    }

    @Test
    void getNotFound() {
        Assertions.assertThrows(NotFoundException.class,
                () -> repository.findById(NOT_FOUND_ID).orElseThrow(() -> new NotFoundException("")));
    }

    @Test
    void getAll() {
        List<E> expected = testData.getAll();
        List<E> actual = repository.findAll();
        Assertions.assertEquals(expected, actual);
    }
}
