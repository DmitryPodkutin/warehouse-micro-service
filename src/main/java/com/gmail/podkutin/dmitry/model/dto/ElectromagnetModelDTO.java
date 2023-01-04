package com.gmail.podkutin.dmitry.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class ElectromagnetModelDTO {

    @NotBlank
    private String model;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectromagnetModelDTO that = (ElectromagnetModelDTO) o;
        return Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }
}
