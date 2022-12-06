package com.gmail.podkutin.dmitry.model;

import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KitOfHydraulicValve that = (KitOfHydraulicValve) o;
        return model.equals(that.model) && amount.equals(that.amount) && price.equals(that.price) && electromagnet.equals(that.electromagnet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, amount, price, electromagnet);
    }
}


