package com.gmail.podkutin.dmitry.model.dto;

import com.gmail.podkutin.dmitry.model.Volt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class ElectromagnetDTO {

    @NotNull
    private ElectromagnetModelDTO electromagnetModel;

    @NotNull
    private Volt voltage;

    @Min(0)
    @Max(100)
    private Integer amount;

    public ElectromagnetDTO(ElectromagnetDTO electromagnetDTO) {
        this.electromagnetModel = new ElectromagnetModelDTO(electromagnetDTO.electromagnetModel.getModel());
        this.voltage = electromagnetDTO.voltage;
        this.amount = electromagnetDTO.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElectromagnetDTO that = (ElectromagnetDTO) o;
        return Objects.equals(electromagnetModel, that.electromagnetModel) && voltage == that.voltage && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(electromagnetModel, voltage, amount);
    }
}
