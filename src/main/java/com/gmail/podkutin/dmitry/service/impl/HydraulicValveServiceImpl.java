package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.model.*;
import com.gmail.podkutin.dmitry.repository.HydraulicValvesRepository;
import com.gmail.podkutin.dmitry.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HydraulicValveServiceImpl extends AbstractService<HydraulicValve, HydraulicValvesRepository> {

    public HydraulicValveServiceImpl(@Autowired HydraulicValvesRepository repository) {
        super(repository);
    }
}
