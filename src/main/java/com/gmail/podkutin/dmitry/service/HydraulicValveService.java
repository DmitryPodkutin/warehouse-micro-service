package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.HydraulicValve;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public interface HydraulicValveService extends CommonService<HydraulicValve> {
    HydraulicValve getByModel(@NotBlank String model);
}
