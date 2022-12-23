package com.gmail.podkutin.dmitry.model.dto;

import com.gmail.podkutin.dmitry.model.Volt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class KitOfHydraulicValveDTO {
    private final String model;
    private final Volt volt;
    private final Integer amount;

    public KitOfHydraulicValveDTO(KitOfHydraulicValveDTO kitOfHydraulicValveDTO) {
        this.model = kitOfHydraulicValveDTO.model;
        this.volt = kitOfHydraulicValveDTO.volt;
        this.amount = kitOfHydraulicValveDTO.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KitOfHydraulicValveDTO that = (KitOfHydraulicValveDTO) o;
        return model.equals(that.model) && volt == that.volt && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, volt, amount);
    }
}
