package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.model.Volt;
import com.gmail.podkutin.dmitry.repository.ElectromagnetsRepository;
import com.gmail.podkutin.dmitry.service.ElectromagnetsService;
import com.gmail.podkutin.dmitry.test_data.ElectromagnetTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import java.math.BigDecimal;

import static com.gmail.podkutin.dmitry.test_data.ElectromagnetTestData.ELECTROMAGNET_1;
import static com.gmail.podkutin.dmitry.test_data.ElectromagnetTestData.*;

@SpringBootTest
class ElectromagnetsServiceImplTest extends AbstractCommonServiceTest<Electromagnet,
        ElectromagnetsRepository, ElectromagnetsServiceImpl, ElectromagnetTestData> {

    private final ElectromagnetsService service;

    public ElectromagnetsServiceImplTest(@Autowired ElectromagnetsServiceImpl service, @Autowired ElectromagnetTestData testData) {
        super(service, testData);
        this.service = service;
    }

    @Test
    void getByModel() {
        Electromagnet actual = service.getByModelAndVoltage(ELECTROMAGNET_1.getElectromagnetModel().getModel()
                , ELECTROMAGNET_1.getVoltage());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(ELECTROMAGNET_1, actual);
    }

    @Test
    void getByModelValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> service.getByModelAndVoltage("", Volt.VOLT_24));
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> service.getByModelAndVoltage("NG6", null));
    }

    @Test
    void createValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(null));
        Electromagnet electromagnet_1 = getClone(ELECTROMAGNET_1);
        electromagnet_1.setElectromagnetModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(electromagnet_1));

        Electromagnet electromagnet_2 = getClone(ELECTROMAGNET_2);
        electromagnet_2.setAmount(-1);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(electromagnet_2));
        electromagnet_2.setAmount(101);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(electromagnet_2));
        electromagnet_2.setAmount(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(electromagnet_2));

        Electromagnet electromagnet_3 = getClone(ELECTROMAGNET_3);
        electromagnet_3.setPrice(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(electromagnet_3));
        electromagnet_3.setPrice(new BigDecimal("125.034"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(electromagnet_3));
        electromagnet_3.setPrice(new BigDecimal("125098.00"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(electromagnet_3));

        Electromagnet electromagnet_4 = getClone(ELECTROMAGNET_4);
        electromagnet_4.setVoltage(null);
    }

    @Test
    void updateValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(null));
        Electromagnet electromagnet_1 = getClone(ELECTROMAGNET_1);
        electromagnet_1.setElectromagnetModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(electromagnet_1));

        Electromagnet electromagnet_2 = getClone(ELECTROMAGNET_2);
        electromagnet_2.setAmount(-1);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(electromagnet_2));
        electromagnet_2.setAmount(101);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(electromagnet_2));
        electromagnet_2.setAmount(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(electromagnet_2));

        Electromagnet electromagnet_3 = getClone(ELECTROMAGNET_3);
        electromagnet_3.setPrice(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(electromagnet_3));
        electromagnet_3.setPrice(new BigDecimal("125.034"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(electromagnet_3));
        electromagnet_3.setPrice(new BigDecimal("125098.00"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(electromagnet_3));

        Electromagnet electromagnet_4 = getClone(ELECTROMAGNET_4);
        electromagnet_4.setVoltage(null);
    }
}