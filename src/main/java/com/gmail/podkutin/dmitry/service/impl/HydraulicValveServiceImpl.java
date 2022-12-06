package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.*;
import com.gmail.podkutin.dmitry.repository.HydraulicValvesRepository;
import com.gmail.podkutin.dmitry.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HydraulicValveServiceImpl extends AbstractService<HydraulicValve, HydraulicValvesRepository> {

    HydraulicValvesRepository repository;

    public HydraulicValveServiceImpl(@Autowired HydraulicValvesRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public HydraulicValve getByModel(String model) {
        return repository.getByModel(model).orElseThrow(() ->
                new NotFoundException("HydraulicValve model +" + model + " Not Found"));
    }
}
