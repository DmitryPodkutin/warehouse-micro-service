package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.AbstractServiceTest;
import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gmail.podkutin.dmitry.test_data.KitOfHydraulicValveTestData.KIT_OF_HYDRAULIC_VALVE_1;
import static com.gmail.podkutin.dmitry.test_data.KitOfHydraulicValveTestData.KIT_OF_HYDRAULIC_VALVE_DTO;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class KitOfHydraulicValveServiceImplTest extends AbstractServiceTest {

    private final KitOfHydraulicValveServiceImpl service;
    private final HydraulicValveServiceImpl hydraulicValveService;
    private final ElectromagnetsServiceImpl electromagnetsService;

    public KitOfHydraulicValveServiceImplTest(@Autowired KitOfHydraulicValveServiceImpl service,
                                              @Autowired HydraulicValveServiceImpl hydraulicValveService,
                                              @Autowired ElectromagnetsServiceImpl electromagnetsService) {
        this.service = service;
        this.hydraulicValveService = hydraulicValveService;
        this.electromagnetsService = electromagnetsService;
    }

    @Test
    void sale() {
        KitOfHydraulicValve actualKit = service.sale(KIT_OF_HYDRAULIC_VALVE_DTO);
        Assertions.assertEquals(0, hydraulicValveService.getByModel(
                KIT_OF_HYDRAULIC_VALVE_DTO.getModel()).getAmount());
        Assertions.assertEquals(6, electromagnetsService.getByModelAndVoltage(
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getElectromagnetModel().getModel(),
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getVoltage()).getAmount());
        Assertions.assertEquals(0,actualKit.getAmount());
        Assertions.assertEquals(0,actualKit.getElectromagnet().getAmount());
    }

    @Test
    void saleException() {
        KitOfHydraulicValveDTO kitOfHydraulicValveDTO = new KitOfHydraulicValveDTO(KIT_OF_HYDRAULIC_VALVE_DTO);
        kitOfHydraulicValveDTO.setAmount(6);
        assertThrows(EquipmentException.class,
                () -> service.sale(kitOfHydraulicValveDTO));
    }

    @Test
    void get() {
        KitOfHydraulicValve actual = service.get(KIT_OF_HYDRAULIC_VALVE_DTO);
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVE_1, actual);
    }

    @Test
    void getAll() {
        service.getAll().forEach(System.out::println);
    }
}