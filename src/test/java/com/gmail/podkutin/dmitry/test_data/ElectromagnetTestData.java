package com.gmail.podkutin.dmitry.test_data;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.ElectromagnetModel;
import com.gmail.podkutin.dmitry.model.Volt;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class ElectromagnetTestData implements TestData<Electromagnet> {

    public static final Electromagnet ELECTROMAGNET_1 = new Electromagnet(100005, new ElectromagnetModel(100001,"NG6"), 10, new BigDecimal("1470.00"), Volt.VOLT_24);
    public static final Electromagnet ELECTROMAGNET_2 = new Electromagnet(100006, new ElectromagnetModel(100001,"NG6"), 10, new BigDecimal("1470.00"), Volt.VOLT_110);
    public static final Electromagnet ELECTROMAGNET_3 = new Electromagnet(100007, new ElectromagnetModel(100001,"NG6"), 10, new BigDecimal("1470.00"), Volt.VOLT_220);
    public static final Electromagnet ELECTROMAGNET_4 = new Electromagnet(100008, new ElectromagnetModel(100001,"NG6"), 10, new BigDecimal("1470.00"), Volt.VOLT_380);

    @Override
    public Electromagnet getFirsEntity() {
        return ELECTROMAGNET_1;
    }

    public  Electromagnet getNew() {
        return Electromagnet.builder()
                .electromagnetModel(new ElectromagnetModel(100001,"NG6"))
                .voltage(Volt.VOLT_440)
                .amount(15)
                .price(new BigDecimal("1470.00"))
                .build();
    }

    public  Electromagnet getUpdated() {
        return Electromagnet.builder()
                .id(ELECTROMAGNET_1.getId())
                .electromagnetModel(new ElectromagnetModel(100001,"NG6"))
                .voltage(Volt.VOLT_36)
                .amount(3)
                .price(new BigDecimal("200.00"))
                .build();
    }

    public   List<Electromagnet> getAll() {
        return Arrays.asList(ELECTROMAGNET_1, ELECTROMAGNET_2, ELECTROMAGNET_3, ELECTROMAGNET_4);
    }
}
