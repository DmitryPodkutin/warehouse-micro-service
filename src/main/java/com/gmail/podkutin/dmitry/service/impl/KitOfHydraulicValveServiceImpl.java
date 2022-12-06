package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.model.*;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.gmail.podkutin.dmitry.util.ElectromagnetUtil.toDto;
import static com.gmail.podkutin.dmitry.util.EquipmentUtil.equipment;

@Service
public class KitOfHydraulicValveServiceImpl {

    private final HydraulicValveServiceImpl hydraulicValveService;

    private final ElectromagnetsServiceImpl electromagnetsService;

    public KitOfHydraulicValveServiceImpl(@Autowired HydraulicValveServiceImpl hydraulicValveService, @Autowired ElectromagnetsServiceImpl electromagnetsService) {
        this.hydraulicValveService = hydraulicValveService;
        this.electromagnetsService = electromagnetsService;
    }

    @Transactional
    KitOfHydraulicValve sale(KitOfHydraulicValveDTO kit) {
        HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kit.getModel());
        Electromagnet electromagnet = electromagnetsService.getByModelAndVoltage(hydraulicValve.getElectromagnetModel().getModel(), kit.getVolt());
        int hydraulicValveAmountAfterDecrement = hydraulicValve.getAmount() - kit.getAmount();
        int electromagnetAmountAfterDecrement = electromagnet.getAmount() - kit.getAmount() * hydraulicValve.getNumberOfElectromagnetsToComplete();
        if (hydraulicValveAmountAfterDecrement >= 0 && electromagnetAmountAfterDecrement >= 0) {
            hydraulicValve.setAmount(hydraulicValveAmountAfterDecrement);
            electromagnet.setAmount(electromagnetAmountAfterDecrement);
            hydraulicValveService.update(hydraulicValve);
            electromagnetsService.update(electromagnet);
        } else {
            throw new EquipmentException(" Not enough spare parts for the kit ");
        }
        return equipment(hydraulicValve, toDto(electromagnet));
    }

    @Transactional
    KitOfHydraulicValve get(KitOfHydraulicValveDTO kit) {
        HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kit.getModel());
        ElectromagnetDTO electromagnet = toDto(electromagnetsService.getByModelAndVoltage(hydraulicValve.getElectromagnetModel().getModel(), kit.getVolt()));
        return equipment(hydraulicValve, electromagnet);
    }

    @Transactional
    List<KitOfHydraulicValve> getAll() {
        List<HydraulicValve> hydraulicValves = hydraulicValveService.getAll();
        List<ElectromagnetDTO> electromagnets = toDto(electromagnetsService.getAll());
        List<KitOfHydraulicValve> kits = new ArrayList<>();
        for (HydraulicValve hydraulicValve : hydraulicValves) {
            for (ElectromagnetDTO electromagnet : electromagnets) {
                kits.add(equipment(hydraulicValve, electromagnet));
            }
        }
        return kits;
    }
}
