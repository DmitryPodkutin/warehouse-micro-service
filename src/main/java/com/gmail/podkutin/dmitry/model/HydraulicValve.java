package com.gmail.podkutin.dmitry.model;

import com.gmail.podkutin.dmitry.model.dto.HydraulicValveTO;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "hydraulic_valves")

public class HydraulicValve extends AbstractBaseEntity {

    @Column(name = "model")
    private String model;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "number_of_electromagnets_to_complete")
    private Integer numberOfElectromagnetsToComplete;

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

    public HydraulicValve(HydraulicValve hydraulicValve) {
        this.model = hydraulicValve.getModel();
        this.amount = hydraulicValve.getAmount();
        this.price = hydraulicValve.price;
        this.numberOfElectromagnetsToComplete = hydraulicValve.getNumberOfElectromagnetsToComplete();
    }

    public HydraulicValve(HydraulicValveTO hydraulicValveTO) {
        this.model = hydraulicValveTO.getModel();
        this.amount = hydraulicValveTO.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        HydraulicValve that = (HydraulicValve) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
