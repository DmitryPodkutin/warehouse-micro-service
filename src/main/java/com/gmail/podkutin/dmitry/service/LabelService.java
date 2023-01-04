package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.Label;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public interface LabelService extends CommonService<Label> {
    Label getByModel(@NotBlank String model);
}
