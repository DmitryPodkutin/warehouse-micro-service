package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.repository.LabelsRepository;
import com.gmail.podkutin.dmitry.test_data.LabelTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gmail.podkutin.dmitry.test_data.LabelTestData.LABEL_1;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LabelServiceImplTest extends AbstractCommonServiceTest<Label,
        LabelsRepository, LabelServiceImpl, LabelTestData> {

    private final  LabelServiceImpl service;
    private final  LabelTestData testData;

    public LabelServiceImplTest(@Autowired LabelServiceImpl service, @Autowired LabelTestData testData) {
        super(service, testData);
        this.service =service;
        this.testData=testData;
    }

    @Override
    @Test
    void save() {
        service.delete(LABEL_1.getId());
        Label expected = testData.getNew();
        service.create(expected);
        Label actual = service.get(expected.getId());
        Assertions.assertEquals(expected, actual);
        Label label = testData.getNew();
        label.setId(NOT_FOUND_ID);
        assertNull(service.create(label));
        assertThrows(NullPointerException.class,
                () -> service.create(null));
    }

    @Override
    @Test
    void update() {
        Label expected = service.update(testData.getUpdated());
        Assertions.assertEquals(expected, service.get(expected.getId()));
        assertThrows(NotFoundException.class,
                () -> service.update(testData.getNew()));
        assertThrows(NullPointerException.class,
                () -> service.update(null));
    }

    @Override
    @Test
    void delete() {
        service.delete(LABEL_1.getId());
        assertThrows(NotFoundException.class,
                () -> service.update(testData.getFirsEntity()));
    }
}