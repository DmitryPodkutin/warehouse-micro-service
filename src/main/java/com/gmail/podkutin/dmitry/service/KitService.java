package com.gmail.podkutin.dmitry.service;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface KitService<E, T> {

    void saveKits(List<@Valid E> kits);

    T saleKits(@Valid E kit);

    T getKitsAvailableForEquipment(@Valid E kit);

    List<T> getListKitsAvailableForEquipment(List<@Valid E> kits);
}