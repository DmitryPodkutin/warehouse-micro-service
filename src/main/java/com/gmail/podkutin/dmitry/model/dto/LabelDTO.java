package com.gmail.podkutin.dmitry.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
public class LabelDTO {

    @NotBlank
    @Pattern(
            regexp = "^[а-яА-Я0-9]+$",
            message = "Не соответствует формату(испльзуйте кирилицу)"
    )
    private String model;

    @NotNull
    @Min(0)
    @Max(1000)
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