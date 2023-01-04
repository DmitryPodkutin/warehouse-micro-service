package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.Volt;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public interface ElectromagnetsService extends CommonService<Electromagnet> {
    Electromagnet getByModelAndVoltage(@NotBlank String model, @NotNull Volt volt);
}
