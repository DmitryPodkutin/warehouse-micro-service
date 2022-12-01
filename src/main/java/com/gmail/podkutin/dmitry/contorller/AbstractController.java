package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.AbstractBaseEntity;
import com.gmail.podkutin.dmitry.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public abstract class AbstractController<E extends AbstractBaseEntity, S extends CommonService<E>>
        implements CommonController<E> {

    private final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> create(@RequestBody E entity) {
        return entity != null
                ? new ResponseEntity<>(service.create(entity), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<E> update(E entity) {
        return entity != null
                ? new ResponseEntity<>(service.update(entity), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public void delete(int id) {
        service.delete(id);
    }

    @Override
    public ResponseEntity<E> get(int id) {
        E entity = service.get(id);
        return entity != null
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<E>> getAll() {
        List<E> entity = service.getAll();
        return entity != null
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}