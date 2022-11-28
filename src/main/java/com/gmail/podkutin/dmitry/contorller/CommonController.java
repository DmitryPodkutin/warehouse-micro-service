package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public interface CommonController<E extends AbstractBaseEntity> {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<E> create(@RequestBody E entity);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<E> update(@RequestBody E entity);

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id);

    @GetMapping("/{id}")
    ResponseEntity<E> get(@PathVariable int id);

    @GetMapping
    ResponseEntity<List<E>> getAll();
}