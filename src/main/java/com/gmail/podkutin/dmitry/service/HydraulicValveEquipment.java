package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;

public interface HydraulicValveEquipment {

    KitOfHydraulicValve assembleKit(HydraulicValve hydraulicValve, Electromagnet electromagnet, Label label);

    void disassembleKit(KitOfHydraulicValveDTO kit);
}
