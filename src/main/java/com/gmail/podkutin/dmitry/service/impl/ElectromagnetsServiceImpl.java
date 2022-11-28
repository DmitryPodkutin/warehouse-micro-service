package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.repository.ElectromagnetsRepository;
import com.gmail.podkutin.dmitry.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectromagnetsServiceImpl extends AbstractService<Electromagnet, ElectromagnetsRepository> {

    public ElectromagnetsServiceImpl(@Autowired ElectromagnetsRepository repository) {
        super(repository);
    }
}
