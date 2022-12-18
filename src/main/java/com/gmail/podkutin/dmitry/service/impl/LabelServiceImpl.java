package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.repository.LabelsRepository;
import com.gmail.podkutin.dmitry.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl extends AbstractService<Label, LabelsRepository> {

    private final LabelsRepository repository;

    public LabelServiceImpl(@Autowired LabelsRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Label getByModel(String model) {
        return repository.getByModel(model).orElseThrow(() ->
                new NotFoundException("Label model +" + model + " Not Found"));
    }
}
