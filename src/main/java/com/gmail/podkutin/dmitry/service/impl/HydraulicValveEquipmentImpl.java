package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetModelDTO;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import com.gmail.podkutin.dmitry.model.dto.LabelDTO;
import com.gmail.podkutin.dmitry.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class HydraulicValveEquipmentImpl implements HydraulicValveEquipment {
    private final HydraulicValveService hydraulicValveService;
    private final ElectromagnetsService electromagnetService;
    private final LabelService labelCommonService;

    public HydraulicValveEquipmentImpl(@Autowired HydraulicValveServiceImpl hydraulicValveService,
                                       @Autowired ElectromagnetsServiceImpl electromagnetService,
                                       @Autowired LabelServiceImpl labelCommonService) {
        this.hydraulicValveService = hydraulicValveService;
        this.electromagnetService = electromagnetService;
        this.labelCommonService = labelCommonService;
    }

    @Override
    public KitOfHydraulicValve assembleKit(HydraulicValve hydraulicValve, Electromagnet electromagnet, Label label) {
        int kitsQuantity = hydraulicValve.getAmount();
        int labelQuantity = label.getAmount();
        int numberOfElectromagnetsToComplete = hydraulicValve.getNumberOfElectromagnetsToComplete();
        int quantityAvailableElectromagnets = electromagnet.getAmount() / numberOfElectromagnetsToComplete;

        if (kitsQuantity <= labelQuantity && kitsQuantity <= quantityAvailableElectromagnets) {
            return setAmount(hydraulicValve, electromagnet, label, kitsQuantity);
        } else if (labelQuantity <= kitsQuantity && labelQuantity <= quantityAvailableElectromagnets) {
            return setAmount(hydraulicValve, electromagnet, label, labelQuantity);
        } else {
            return setAmount(hydraulicValve, electromagnet, label, quantityAvailableElectromagnets);
        }
    }

    @Override
    @Transactional
    public void disassembleKit(KitOfHydraulicValveDTO kit) {
        String kitModel = kit.getModel();
        HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kitModel);
        Electromagnet electromagnet = electromagnetService.getByModelAndVoltage(Objects.requireNonNull(
                hydraulicValve.getElectromagnetModel().getModel(), "HydraulicValve Not Be Null"), kit.getVolt());
        Label label = labelCommonService.getByModel(kitModel);
        Integer kitAmount = Objects.requireNonNull(kit.getAmount(), "Kit amount Not Be Null");
        hydraulicValve.setAmount(hydraulicValve.getAmount() + kitAmount);
        electromagnet.setAmount(electromagnet.getAmount() +
                hydraulicValve.getNumberOfElectromagnetsToComplete() * kitAmount);
        label.setAmount(label.getAmount() + kitAmount);
    }

    private static KitOfHydraulicValve setAmount(HydraulicValve hydraulicValve, Electromagnet electromagnet
            , Label label, int amount) {
        hydraulicValve.setAmount(hydraulicValve.getAmount() - amount);
        electromagnet.setAmount(electromagnet.getAmount() - hydraulicValve.getNumberOfElectromagnetsToComplete() * amount);
        label.setAmount(label.getAmount() - amount);
        return new KitOfHydraulicValve(hydraulicValve.getModel(), amount, hydraulicValve.getPrice()
                , new ElectromagnetDTO(new ElectromagnetDTO(
                new ElectromagnetModelDTO(electromagnet.getElectromagnetModel().getModel())
                , electromagnet.getVoltage(), hydraulicValve.getNumberOfElectromagnetsToComplete() * amount))
                , new LabelDTO(label.getModel(), amount));
    }

}
