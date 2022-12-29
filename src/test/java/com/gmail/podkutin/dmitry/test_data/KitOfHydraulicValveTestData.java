package com.gmail.podkutin.dmitry.test_data;

import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Volt;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetModelDTO;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import com.gmail.podkutin.dmitry.model.dto.LabelDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class KitOfHydraulicValveTestData {

    public static final KitOfHydraulicValve KIT_OF_HYDRAULIC_VALVE_1 = new KitOfHydraulicValve("РХ06574А1ОФ"
            , 2, new BigDecimal("4920.00"), new ElectromagnetDTO(new ElectromagnetModelDTO("NG6")
            , Volt.VOLT_24, 4), LabelDTO.builder().model("РХ06574А1ОФ").amount(2).build());

    public static final KitOfHydraulicValveDTO KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SAVE = new KitOfHydraulicValveDTO(
            "РХ06574А1", Volt.VOLT_24, 8);

    public static final KitOfHydraulicValveDTO KIT_OF_HYDRAULIC_VALVE_DTO_FOR_SALE = new KitOfHydraulicValveDTO(
            "РХ06574А1ОФ", Volt.VOLT_24, 5);

    public static final KitOfHydraulicValveDTO KIT_OF_HYDRAULIC_VALVE_DTO = KitOfHydraulicValveDTO.builder()
            .model("РХ06574А1ОФ").volt(Volt.VOLT_24).build();

    public static final KitOfHydraulicValveDTO KIT_OF_HYDRAULIC_VALVE_DTO_WITH_AMOUNT = KitOfHydraulicValveDTO.builder()
            .model("РХ06574А1ОФ").amount(3).volt(Volt.VOLT_24).build();

    public static final List<KitOfHydraulicValveDTO> REQUEST_KITS = Arrays.asList(
            KitOfHydraulicValveDTO.builder()
                    .model("РХ06574А1ОФ")
                    .volt(Volt.VOLT_24).build(),
            KitOfHydraulicValveDTO.builder()
                    .model("РХ06341")
                    .volt(Volt.VOLT_24).build(),
            KitOfHydraulicValveDTO.builder()
                    .model("РХ06341")
                    .volt(Volt.VOLT_110).build(),
            KitOfHydraulicValveDTO.builder()
                    .model("РХ06574А1")
                    .volt(Volt.VOLT_24).build(),
            KitOfHydraulicValveDTO.builder()
                    .model("РХ06341")
                    .volt(Volt.VOLT_24).build()
    );

    public static final List<KitOfHydraulicValve> KIT_OF_HYDRAULIC_VALVES = Arrays.asList(
            KitOfHydraulicValve.builder()
                    .model("РХ06574А1ОФ")
                    .amount(2)
                    .price(new BigDecimal("4920.00"))
                    .electromagnet(ElectromagnetDTO.builder()
                            .electromagnetModel(new ElectromagnetModelDTO("NG6"))
                            .voltage(Volt.VOLT_24)
                            .amount(4).build())
                    .label(LabelDTO.builder()
                            .model("РХ06574А1ОФ")
                            .amount(2).build()).build(),
            KitOfHydraulicValve.builder()
                    .model("РХ06341")
                    .amount(2)
                    .price(new BigDecimal("4920.00"))
                    .electromagnet(ElectromagnetDTO.builder()
                            .electromagnetModel(new ElectromagnetModelDTO("NG6"))
                            .voltage(Volt.VOLT_24)
                            .amount(4).build())
                    .label(LabelDTO.builder()
                            .model("РХ06341")
                            .amount(2).build()).build(),
            KitOfHydraulicValve.builder()
                    .model("РХ06341")
                    .amount(0)
                    .price(new BigDecimal("4920.00"))
                    .electromagnet(ElectromagnetDTO.builder()
                            .electromagnetModel(new ElectromagnetModelDTO("NG6"))
                            .voltage(Volt.VOLT_110)
                            .amount(0).build())
                    .label(LabelDTO.builder()
                            .model("РХ06341")
                            .amount(0).build()).build(),
            KitOfHydraulicValve.builder()
                    .model("РХ06574А1")
                    .amount(2)
                    .price(new BigDecimal("4470.00"))
                    .electromagnet(ElectromagnetDTO.builder()
                            .electromagnetModel(new ElectromagnetModelDTO("NG6"))
                            .voltage(Volt.VOLT_24)
                            .amount(2).build())
                    .label(LabelDTO.builder()
                            .model("РХ06574А1")
                            .amount(2).build()).build(),
            KitOfHydraulicValve.builder()
                    .model("РХ06341")
                    .amount(0)
                    .price(new BigDecimal("4920.00"))
                    .electromagnet(ElectromagnetDTO.builder()
                            .electromagnetModel(new ElectromagnetModelDTO("NG6"))
                            .voltage(Volt.VOLT_24)
                            .amount(0).build())
                    .label(LabelDTO.builder()
                            .model("РХ06341")
                            .amount(0).build()).build()
    );
}
