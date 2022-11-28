package com.gmail.podkutin.dmitry.test_data;

import com.gmail.podkutin.dmitry.model.ElectromagnetModel;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class HydraulicValveTestData implements TestData<HydraulicValve> {

    public static final HydraulicValve HYDRAULIC_VALVE_1 = new HydraulicValve(100009, "РХ06574А1", 10, new BigDecimal("4470.00"), 1, new ElectromagnetModel(100001, "NG6"));
    public static final HydraulicValve HYDRAULIC_VALVE_2 = new HydraulicValve(100010, "PX06574A1ОФ", 2, new BigDecimal("4920.00"), 2, new ElectromagnetModel(100001, "NG6"));
    public static final HydraulicValve HYDRAULIC_VALVE_3 = new HydraulicValve(100011, "РХ06574А1", 2, new BigDecimal("4920.00"), 2, new ElectromagnetModel(100001, "NG6"));
    public static final HydraulicValve HYDRAULIC_VALVE_4 = new HydraulicValve(100012, "РХ06574А1", 2, new BigDecimal("4920.00"), 2, new ElectromagnetModel(100001, "NG6"));

    @Override
    public HydraulicValve getFirsEntity() {
        return HYDRAULIC_VALVE_1;
    }

    @Override
    public HydraulicValve getNew() {
        return HydraulicValve.builder()
                .model("TestModel")
                .amount(16)
                .price(new BigDecimal("4920.00"))
                .numberOfElectromagnetsToComplete(2)
                .electromagnetModel(new ElectromagnetModel(100001, "NG6"))
                .build();
    }

    @Override
    public HydraulicValve getUpdated() {
        return HydraulicValve.builder()
                .id(100009)
                .model("UpdateModel")
                .amount(3)
                .price(new BigDecimal("56"))
                .numberOfElectromagnetsToComplete(2)
                .electromagnetModel(new ElectromagnetModel(100001, "NG6"))
                .build();
    }

    @Override
    public List<HydraulicValve> getAll() {
        return Arrays.asList(HYDRAULIC_VALVE_1, HYDRAULIC_VALVE_2, HYDRAULIC_VALVE_3, HYDRAULIC_VALVE_4);
    }
}
