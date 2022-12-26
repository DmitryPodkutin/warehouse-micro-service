package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.AbstractServiceTest;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Volt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.gmail.podkutin.dmitry.test_data.ElectromagnetTestData.ELECTROMAGNET_1;
import static com.gmail.podkutin.dmitry.test_data.HydraulicValveTestData.HYDRAULIC_VALVE_1;
import static com.gmail.podkutin.dmitry.test_data.KitOfHydraulicValveTestData.KIT_OF_HYDRAULIC_VALVE_DTO_WITH_AMOUNT;
import static com.gmail.podkutin.dmitry.test_data.LabelTestData.LABEL_1;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HydraulicValveEquipmentImplTest extends AbstractServiceTest {

    @Autowired
    HydraulicValveEquipmentImpl equipment;
    @Autowired
    HydraulicValveServiceImpl hydraulicValveService;
    @Autowired
    ElectromagnetsServiceImpl electromagnetsService;
    @Autowired
    LabelServiceImpl labelService;

    @Test
    void assembleKit() {
        KitOfHydraulicValve kit = equipment.assembleKit(HYDRAULIC_VALVE_1, ELECTROMAGNET_1, LABEL_1);
        assertNotNull(kit);
        Assertions.assertEquals(10,kit.getAmount());
        Assertions.assertEquals(10,kit.getElectromagnet().getAmount());
        Assertions.assertEquals(10,kit.getLabel().getAmount());
    }

    @Test
    void disassembleKit() {
        equipment.disassembleKit(KIT_OF_HYDRAULIC_VALVE_DTO_WITH_AMOUNT);
        String model = KIT_OF_HYDRAULIC_VALVE_DTO_WITH_AMOUNT.getModel();
        Volt volt = KIT_OF_HYDRAULIC_VALVE_DTO_WITH_AMOUNT.getVolt();

        Assertions.assertEquals(5, hydraulicValveService.getByModel(model).getAmount());
        Assertions.assertEquals(16,electromagnetsService.getByModelAndVoltage("NG6",volt).getAmount());
        Assertions.assertEquals(13,labelService.getByModel(model).getAmount());
    }
}