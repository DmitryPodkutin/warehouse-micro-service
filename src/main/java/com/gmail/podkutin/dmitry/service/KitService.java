package com.gmail.podkutin.dmitry.service;

import java.util.List;

public interface KitService<E, T> {

    void saveKits(List<E> kits);

    T saleKits(E kit);

    T getKitsAvailableForEquipment(E kit);

    List<T> getListKitsAvailableForEquipment(List<E> kits);
}