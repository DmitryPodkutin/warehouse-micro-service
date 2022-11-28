package com.gmail.podkutin.dmitry.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "electromagnets")
public class Electromagnet extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ElectromagnetModel electromagnetModel;

    @Enumerated(EnumType.STRING)
    private Volt voltage;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private BigDecimal price;

    @Builder
    public Electromagnet(Integer id, ElectromagnetModel electromagnetModel, Integer amount, BigDecimal price, Volt voltage) {
        super(id);
        this.electromagnetModel = electromagnetModel;
        this.amount = amount;
        this.price = price;
        this.voltage = voltage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Electromagnet that = (Electromagnet) o;
        return electromagnetModel.equals(that.electromagnetModel) && voltage == that.voltage && amount.equals(that.amount) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), electromagnetModel, voltage, amount, price);
    }
}
