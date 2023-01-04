package com.gmail.podkutin.dmitry.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "hydraulic_valves")
public class HydraulicValve extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "model")
    private String model;

    @NotNull
    @Min(0)
    @Max(100)
    @Column(name = "amount")
    private Integer amount;

    @NotNull
    @DecimalMin(value = "0.00", inclusive = true)
    @Digits(integer = 5, fraction = 2)
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Min(1)
    @Max(2)
    @Column(name = "number_of_electromagnets_to_complete")
    private Integer numberOfElectromagnetsToComplete;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "model_id")
    private ElectromagnetModel electromagnetModel;

    @Builder
    public HydraulicValve(Integer id, String model, Integer amount, BigDecimal price, Integer numberOfElectromagnetsToComplete, ElectromagnetModel electromagnetModel) {
        super(id);
        this.model = model;
        this.amount = amount;
        this.price = price;
        this.numberOfElectromagnetsToComplete = numberOfElectromagnetsToComplete;
        this.electromagnetModel = electromagnetModel;
    }

    public String model() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HydraulicValve that = (HydraulicValve) o;
        return model.equals(that.model) && amount.equals(that.amount) && price.equals(that.price) && numberOfElectromagnetsToComplete.equals(that.numberOfElectromagnetsToComplete) && electromagnetModel.equals(that.electromagnetModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, amount, price, numberOfElectromagnetsToComplete, electromagnetModel);
    }
}
