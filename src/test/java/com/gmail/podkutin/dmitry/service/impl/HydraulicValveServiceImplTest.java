package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.repository.HydraulicValvesRepository;
import com.gmail.podkutin.dmitry.test_data.HydraulicValveTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static com.gmail.podkutin.dmitry.test_data.HydraulicValveTestData.*;

@SpringBootTest
class HydraulicValveServiceImplTest extends AbstractCommonServiceTest<HydraulicValve,
        HydraulicValvesRepository, HydraulicValveServiceImpl, HydraulicValveTestData> {
    private final HydraulicValveServiceImpl service;


    public HydraulicValveServiceImplTest(@Autowired HydraulicValveServiceImpl service
            , @Autowired HydraulicValveTestData testData) {
        super(service, testData);
        this.service = service;
    }

    @Test
    void createValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(null));
        HydraulicValve hydraulicValve_1 = getClone(HYDRAULIC_VALVE_1);
        hydraulicValve_1.setModel("");
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_1));
        hydraulicValve_1.setModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_1));

        HydraulicValve hydraulicValve_2 = getClone(HYDRAULIC_VALVE_2);
        hydraulicValve_2.setAmount(-1);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_2));
        hydraulicValve_2.setAmount(101);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_2));
        hydraulicValve_2.setAmount(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_2));

        HydraulicValve hydraulicValve_3 = getClone(HYDRAULIC_VALVE_3);
        hydraulicValve_3.setPrice(new BigDecimal("125.034"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_3));
        hydraulicValve_3.setPrice(new BigDecimal("125098.00"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_3));

        HydraulicValve hydraulicValve_4 = getClone(HYDRAULIC_VALVE_4);
        hydraulicValve_4.setNumberOfElectromagnetsToComplete(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_4));
        hydraulicValve_4.setNumberOfElectromagnetsToComplete(0);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_4));
        hydraulicValve_4.setNumberOfElectromagnetsToComplete(3);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_4));

        HydraulicValve hydraulicValve_5 = getClone(HYDRAULIC_VALVE_1);
        hydraulicValve_5.setElectromagnetModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.create(hydraulicValve_5));
    }

    @Test
    void updateValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(null));
        HydraulicValve hydraulicValve_1 = getClone(HYDRAULIC_VALVE_1);
        hydraulicValve_1.setModel("");
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_1));
        hydraulicValve_1.setModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_1));

        HydraulicValve hydraulicValve_2 = getClone(HYDRAULIC_VALVE_2);
        hydraulicValve_2.setAmount(-1);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_2));
        hydraulicValve_2.setAmount(101);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_2));
        hydraulicValve_2.setAmount(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_2));

        HydraulicValve hydraulicValve_3 = getClone(HYDRAULIC_VALVE_3);
        hydraulicValve_3.setPrice(new BigDecimal("125.034"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_3));
        hydraulicValve_3.setPrice(new BigDecimal("125098.00"));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_3));

        HydraulicValve hydraulicValve_4 = getClone(HYDRAULIC_VALVE_4);
        hydraulicValve_4.setNumberOfElectromagnetsToComplete(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_4));
        hydraulicValve_4.setNumberOfElectromagnetsToComplete(0);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_4));
        hydraulicValve_4.setNumberOfElectromagnetsToComplete(3);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_4));

        HydraulicValve hydraulicValve_5 = getClone(HYDRAULIC_VALVE_1);
        hydraulicValve_5.setElectromagnetModel(null);
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.update(hydraulicValve_5));
    }

    @Test
    void getByModel() {
        HydraulicValve actual = service.getByModel(HYDRAULIC_VALVE_2.getModel());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(HYDRAULIC_VALVE_2, actual);
    }

    @Test
    void getByModelValidationException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.getByModel("  "));
        Assertions.assertThrows(ConstraintViolationException.class, () -> service.getByModel(null));
    }
}