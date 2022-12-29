package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.AbstractServiceTest;
import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.gmail.podkutin.dmitry.test_data.KitOfHydraulicValveTestData.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class KitServiceImplTest extends AbstractServiceTest {

    private final KitOfHydraulicValveServiceImpl service;
    private final HydraulicValveServiceImpl hydraulicValveService;
    private final ElectromagnetsServiceImpl electromagnetsService;

    private final LabelServiceImpl labelService;

    public KitServiceImplTest(@Autowired KitOfHydraulicValveServiceImpl service,
                              @Autowired HydraulicValveServiceImpl hydraulicValveService,
                              @Autowired ElectromagnetsServiceImpl electromagnetsService,
                              @Autowired LabelServiceImpl labelService) {
        this.service = service;
        this.hydraulicValveService = hydraulicValveService;
        this.electromagnetsService = electromagnetsService;
        this.labelService = labelService;
    }

    @Test
    void saveKits() {
        service.saveKits(List.of(KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SALE));
        Assertions.assertEquals(7, hydraulicValveService.getByModel(
                KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SALE.getModel()).getAmount());
        Assertions.assertEquals(20, electromagnetsService.getByModelAndVoltage(
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getElectromagnetModel().getModel(),
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getVoltage()).getAmount());
        Assertions.assertEquals(15, labelService.getByModel(KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SALE.getModel()).getAmount());
    }

    @Test
    void saleKits() {
        service.saleKits(KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SAVE);
        Assertions.assertEquals(2, hydraulicValveService.getByModel(
                KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SAVE.getModel()).getAmount());
        Assertions.assertEquals(2, electromagnetsService.getByModelAndVoltage(
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getElectromagnetModel().getModel(),
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getVoltage()).getAmount());
        Assertions.assertEquals(2, labelService.getByModel(KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SAVE.getModel()).getAmount());
    }

    @Test
    void saleException() {
        KitOfHydraulicValveDTO kitOfHydraulicValveDTO = new KitOfHydraulicValveDTO(KIT_OF_HYDRAULIC_VALVE_DTO.getModel()
                , KIT_OF_HYDRAULIC_VALVE_DTO.getVolt(), 6);
        assertThrows(EquipmentException.class,
                () -> service.saleKits(kitOfHydraulicValveDTO));
    }

    @Test
    void getKitsAvailableForEquipment() {
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVE_1, service.getKitsAvailableForEquipment(KIT_OF_HYDRAULIC_VALVE_DTO));
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVE_1, service.getKitsAvailableForEquipment(KIT_OF_HYDRAULIC_VALVE_DTO));
    }

    @Test
    void getListKitsAvailableForEquipment() {
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVES, service.getListKitsAvailableForEquipment(REQUEST_KITS));
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVES, service.getListKitsAvailableForEquipment(REQUEST_KITS));
    }
}