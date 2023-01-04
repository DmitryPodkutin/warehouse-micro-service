package com.gmail.podkutin.dmitry.model;

import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.LabelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class KitOfHydraulicValve {

    @NotEmpty
    @Pattern(
            regexp = "^[а-яА-Я0-9]+$",
            message = "Не соответствует формату(испльзуйте кирилицу)"
    )
    private String model;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer amount;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 5, fraction = 2)
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    private ElectromagnetDTO electromagnet;

    @NotNull
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


