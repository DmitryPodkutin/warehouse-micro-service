package com.gmail.podkutin.dmitry.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class LabelDTO  {
    private String model;
    private Integer amount;

    @Builder
    public LabelDTO(Integer id, String model, Integer amount) {
         this.model = model;
        this.amount = amount;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelDTO labelDTO = (LabelDTO) o;
        return model.equals(labelDTO.model) && amount.equals(labelDTO.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, amount);
    }
}