package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.repository.LabelsRepository;
import com.gmail.podkutin.dmitry.test_data.LabelTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static com.gmail.podkutin.dmitry.test_data.LabelTestData.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class LabelServiceImplTest extends AbstractCommonServiceTest<Label,
        LabelsRepository, LabelServiceImpl, LabelTestData> {

    private final LabelServiceImpl service;
    private final LabelTestData testData;

    public LabelServiceImplTest(@Autowired LabelServiceImpl service, @Autowired LabelTestData testData) {
        super(service, testData);
        this.service = service;
        this.testData = testData;
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
        assertThrows(ConstraintViolationException.class,
                () -> service.create(null));
    }

    @Override
    @Test
    void delete() {
        service.delete(LABEL_1.getId());
        assertThrows(NotFoundException.class,
                () -> service.update(testData.getFirsEntity()));
    }

    @Test
    void getByModel() {
        Label actual = service.getByModel(LABEL_1.getModel());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(LABEL_1, actual);
    }

    @Test
    void getByModelValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.getByModel("  "));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.getByModel(null));
    }

    @Test
    void createValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(null));
        Label label_1 = getClone(LABEL_1);
        label_1.setModel("");
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_1));
        label_1.setModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_1));

        Label label_2 = getClone(LABEL_2);
        label_2.setAmount(-1);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_2));
        label_2.setAmount(1001);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_2));
        label_2.setAmount(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_2));

        Label label_3 = getClone(LABEL_3);
        label_3.setHydraulic_valve_id(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_3));
        label_3.setHydraulic_valve_id(0);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_3));
    }

    @Test
    void updateValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(null));
        Label label_1 = getClone(LABEL_1);
        label_1.setModel("");
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(label_1));
        label_1.setModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(label_1));

        Label label_2 = getClone(LABEL_2);
        label_2.setAmount(-1);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(label_2));
        label_2.setAmount(1001);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(label_2));
        label_2.setAmount(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(label_2));

        Label label_3 = getClone(LABEL_3);
        label_3.setHydraulic_valve_id(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_3));
        label_3.setHydraulic_valve_id(0);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(label_3));
    }
}