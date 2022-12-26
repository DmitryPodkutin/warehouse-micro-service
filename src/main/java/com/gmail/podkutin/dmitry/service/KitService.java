package com.gmail.podkutin.dmitry.service;

import java.util.List;

public interface KitService<E, T> {

    void sale(E kit);

    T get(E kit);

    List<T> getList(List<E> kits);

    void save(List<E> kits);
}