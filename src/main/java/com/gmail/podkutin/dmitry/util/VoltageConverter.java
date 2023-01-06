package com.gmail.podkutin.dmitry.util;

import com.gmail.podkutin.dmitry.model.Volt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VoltageConverter implements Converter<String, Volt> {

    //https://stackoverflow.com/questions/39774427/springs-requestparam-with-enum
    @Override
    public Volt convert(@Nullable String voltage) {
        return Volt.getVoltByVoltage(Integer.valueOf(Objects.requireNonNull(voltage)));
    }
}
