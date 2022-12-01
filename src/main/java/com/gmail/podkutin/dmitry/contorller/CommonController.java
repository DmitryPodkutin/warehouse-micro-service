package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
public interface CommonController<E extends AbstractBaseEntity> {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('write')")
    ResponseEntity<E> create(@RequestBody E entity);

    @PreAuthorize("hasAuthority('write')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<E> update(@RequestBody E entity);

    @PreAuthorize("hasAuthority('write')")
    @DeleteMapping("/{id}")
    void delete(@PathVariable int id);

    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/{id}")
    ResponseEntity<E> get(@PathVariable int id);

    @PreAuthorize("hasAuthority('read')")
    @GetMapping
    ResponseEntity<List<E>> getAll();
}