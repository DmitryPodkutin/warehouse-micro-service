package com.gmail.podkutin.dmitry.model;

import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.LabelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class KitOfHydraulicValve {
    private String model;
    private Integer amount;
    private BigDecimal price;
    private ElectromagnetDTO electromagnet;
    private LabelDTO label;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KitOfHydraulicValve kit = (KitOfHydraulicValve) o;
        return model.equals(kit.model) && amount.equals(kit.amount) && price.equals(kit.price) && electromagnet.equals(kit.electromagnet) && label.equals(kit.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, amount, price, electromagnet, label);
    }
}


