package com.gmail.podkutin.dmitry.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "electromagnets_models")
public class ElectromagnetModel extends AbstractBaseEntity {
    private String model;

    @Builder
    public ElectromagnetModel(Integer id, String model) {
        super(id);
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ElectromagnetModel that = (ElectromagnetModel) o;
        return model.equals(that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), model);
    }
}
