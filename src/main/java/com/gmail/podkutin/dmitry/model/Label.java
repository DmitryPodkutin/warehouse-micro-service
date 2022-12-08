package com.gmail.podkutin.dmitry.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "labels")
public class Label extends AbstractBaseEntity {

    @NotBlank
    @Column(name = "model_name")
    private String model;

    @Min(1)
    @Max(1000)
    @Column(name = "amount")
    private Integer amount;

    @Column(name = "hydraulic_valve_id")
    private Integer hydraulic_valve_id;

    @Builder
    public Label(Integer id, String model, Integer amount, Integer hydraulic_valve_id) {
        super(id);
        this.model = model;
        this.amount = amount;
        this.hydraulic_valve_id = hydraulic_valve_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Label label = (Label) o;
        return model.equals(label.model) && amount.equals(label.amount) && hydraulic_valve_id.equals(label.hydraulic_valve_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model, amount, hydraulic_valve_id);
    }
}