package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.Volt;

public interface ElectromagnetsService extends CommonService<Electromagnet> {
    Electromagnet getByModelAndVoltage(String model, Volt volt);
}
