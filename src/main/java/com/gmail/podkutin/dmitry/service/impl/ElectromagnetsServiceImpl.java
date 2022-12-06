package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.Volt;
import com.gmail.podkutin.dmitry.repository.ElectromagnetsRepository;
import com.gmail.podkutin.dmitry.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ElectromagnetsServiceImpl extends AbstractService<Electromagnet, ElectromagnetsRepository> {

    private final ElectromagnetsRepository repository;

    public ElectromagnetsServiceImpl(@Autowired ElectromagnetsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Electromagnet getByModelAndVoltage(String model, Volt volt) {
       return repository.getByModel(model).orElse(new ArrayList<>())
               .stream().filter(e->e.getVoltage().equals(volt)).findFirst()
               .orElseThrow(() ->
                       new NotFoundException("Electromagnet model +" + model + " Not Found"));
    }
}
