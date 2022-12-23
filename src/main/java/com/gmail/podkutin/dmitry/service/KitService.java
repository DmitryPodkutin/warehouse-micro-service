package com.gmail.podkutin.dmitry.service;

import java.util.List;

public interface KitService<E, T> {

    T sale(E kit);

    T get(E kit);

    List<T> getAll();

    List<T> getAllFiltered(List<E> kits);
}
