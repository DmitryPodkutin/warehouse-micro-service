package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.AbstractServiceTest;
import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void sale() {
        service.sale(KIT_OF_HYDRAULIC_VALVE_DTO_SALE);
        Assertions.assertEquals(5, hydraulicValveService.getByModel(
                KIT_OF_HYDRAULIC_VALVE_DTO_SALE.getModel()).getAmount());
        Assertions.assertEquals(5, electromagnetsService.getByModelAndVoltage(
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getElectromagnetModel().getModel(),
                KIT_OF_HYDRAULIC_VALVE_1.getElectromagnet().getVoltage()).getAmount());
        Assertions.assertEquals(5,labelService.getByModel(KIT_OF_HYDRAULIC_VALVE_DTO_SALE.getModel()).getAmount());

//        Assertions.assertEquals(0, actualKit.getAmount());
//        Assertions.assertEquals(0, actualKit.getElectromagnet().getAmount());
//        Assertions.assertEquals(0, actualKit.getLabel().getAmount());
    }

    @Test
    void saleException() {
        KitOfHydraulicValveDTO kitOfHydraulicValveDTO = new KitOfHydraulicValveDTO(KIT_OF_HYDRAULIC_VALVE_DTO.getModel()
        ,KIT_OF_HYDRAULIC_VALVE_DTO.getVolt(),6);
        assertThrows(EquipmentException.class,
                () -> service.sale(kitOfHydraulicValveDTO));
    }

    @Test
    void get() {
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVE_1, service.get(KIT_OF_HYDRAULIC_VALVE_DTO));
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVE_1, service.get(KIT_OF_HYDRAULIC_VALVE_DTO));
    }

    @Test
    void getAllFiltered() {
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVES, service.getAllFiltered(REQUEST_KITS));
        Assertions.assertEquals(KIT_OF_HYDRAULIC_VALVES, service.getAllFiltered(REQUEST_KITS));
    }
}