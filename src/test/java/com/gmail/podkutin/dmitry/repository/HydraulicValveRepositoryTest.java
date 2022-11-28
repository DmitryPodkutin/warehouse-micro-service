package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.test_data.HydraulicValveTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class HydraulicValveRepositoryTest extends AbstractCommonRepositoryTest<HydraulicValve, HydraulicValvesRepository, HydraulicValveTestData> {

    private final HydraulicValvesRepository repository;

    private final HydraulicValveTestData testData;


    public HydraulicValveRepositoryTest(@Autowired HydraulicValvesRepository repository, @Autowired HydraulicValveTestData testData) {
        super(repository, testData);
        this.repository = repository;
        this.testData = testData;
    }

    @Test
    @Transactional
    void delete() {
        HydraulicValve expected = repository.save(testData.getNew());
        repository.delete(expected.getId());
        assertThrows(NotFoundException.class,
                () -> repository.findById(expected.getId()).orElseThrow(() -> new NotFoundException("")));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertEquals(0, repository.delete(NOT_FOUND_ID));
    }
}