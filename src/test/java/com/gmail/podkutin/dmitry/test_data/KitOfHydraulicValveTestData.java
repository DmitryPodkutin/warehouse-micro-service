package com.gmail.podkutin.dmitry.test_data;

import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Volt;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetModelDTO;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;

import java.math.BigDecimal;

public class KitOfHydraulicValveTestData {

    public static final KitOfHydraulicValve KIT_OF_HYDRAULIC_VALVE_1 = new KitOfHydraulicValve("РХ06574А1ОФ"
            , 2, new BigDecimal("4920.00"), new ElectromagnetDTO(new ElectromagnetModelDTO("NG6")
            , Volt.VOLT_24, 4));

    public static final KitOfHydraulicValveDTO KIT_OF_HYDRAULIC_VALVE_DTO = new KitOfHydraulicValveDTO(
            "РХ06574А1ОФ", Volt.VOLT_24,2);
}
