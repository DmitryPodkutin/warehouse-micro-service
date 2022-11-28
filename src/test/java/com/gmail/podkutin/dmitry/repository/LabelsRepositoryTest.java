package com.gmail.podkutin.dmitry.repository;

import com.gmail.podkutin.dmitry.test_data.LabelTestData;
import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.Label;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static com.gmail.podkutin.dmitry.test_data.LabelTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LabelsRepositoryTest extends AbstractCommonRepositoryTest<Label, LabelsRepository, LabelTestData> {

    private final LabelsRepository repository;

    public LabelsRepositoryTest(@Autowired LabelsRepository repository, @Autowired LabelTestData testData) {
        super(repository, testData);
        this.repository = repository;
    }

    @Test
    @Transactional
    void delete() {
        repository.delete(LABEL_1);
        assertThrows(NotFoundException.class,
                () -> repository.findById(LABEL_1.getId())
                        .orElseThrow(() -> new NotFoundException("")));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertEquals(0, repository.delete(NOT_FOUND_ID));
    }
}