package com.gmail.podkutin.dmitry.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public enum Volt {
    VOLT_12(12),
    VOLT_24(24),
    VOLT_36(36),
    VOLT_110(110),
    VOLT_220(220),
    VOLT_380(380),
    VOLT_440(440);

    private final int volt;

    Volt(int volt) {
        this.volt = volt;
    }

    private final static Map<Integer, Volt> voltages = Arrays.stream(Volt.values())
            .collect(Collectors.toMap(k -> k.volt, v -> v));

    public static Volt getVoltByVoltage(Integer voltage) {
        return voltages.get(voltage);
    }

}
