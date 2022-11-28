package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.test_data.ElectromagnetTestData;
import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.Electromagnet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ElectromagnetsRepositoryTest extends AbstractCommonRepositoryTest<Electromagnet, ElectromagnetsRepository, ElectromagnetTestData> {

    private final ElectromagnetsRepository repository;

    private final ElectromagnetTestData testData;

    public ElectromagnetsRepositoryTest(@Autowired ElectromagnetsRepository repository, @Autowired ElectromagnetTestData testData) {
        super(repository, testData);
        this.repository = repository;
        this.testData = testData;
    }

    @Test
    @Transactional
    void delete() {
        Electromagnet expected = testData.getNew();
        repository.save(expected);
        repository.delete(expected.getId());
        assertThrows(NotFoundException.class,
                () -> repository.findById(expected.getId())
                        .orElseThrow(() -> new NotFoundException("")));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertEquals(0, repository.delete(NOT_FOUND_ID));
    }
}
