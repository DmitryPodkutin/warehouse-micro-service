package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import com.gmail.podkutin.dmitry.service.HydraulicValveEquipment;
import com.gmail.podkutin.dmitry.service.KitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gmail.podkutin.dmitry.util.ValidationUtil.checkNotFound;

@Service
public class KitOfHydraulicValveServiceImpl implements KitService<KitOfHydraulicValveDTO, KitOfHydraulicValve> {

    private final HydraulicValveServiceImpl hydraulicValveService;

    private final ElectromagnetsServiceImpl electromagnetsService;

    private final LabelServiceImpl labelService;

    private final HydraulicValveEquipment equipment;

    public KitOfHydraulicValveServiceImpl(@Autowired HydraulicValveServiceImpl hydraulicValveService,
                                          @Autowired ElectromagnetsServiceImpl electromagnetsService,
                                          @Autowired LabelServiceImpl labelService,
                                          @Autowired HydraulicValveEquipmentImpl equipment) {
        this.hydraulicValveService = hydraulicValveService;
        this.electromagnetsService = electromagnetsService;
        this.labelService = labelService;
        this.equipment = equipment;
    }

    @Override
    public void save(List<KitOfHydraulicValveDTO> kit) {
        kit.forEach(equipment::disassembleKit);
    }

    @Transactional
    public void sale(KitOfHydraulicValveDTO kit) {
        HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kit.getModel());
        Electromagnet electromagnet = electromagnetsService.getByModelAndVoltage(hydraulicValve.getElectromagnetModel()
                .getModel(), kit.getVolt());
        Label label = labelService.getByModel(kit.getModel());
        int hydraulicValveAmountAfterDecrement = hydraulicValve.getAmount() - kit.getAmount();
        int labelAmountAfterDecrement = label.getAmount() - kit.getAmount();
        int electromagnetAmountAfterDecrement = electromagnet.getAmount() - kit.getAmount() * hydraulicValve.getNumberOfElectromagnetsToComplete();
        if (hydraulicValveAmountAfterDecrement >= 0 && electromagnetAmountAfterDecrement >= 0 && labelAmountAfterDecrement >= 0) {
            hydraulicValve.setAmount(hydraulicValveAmountAfterDecrement);
            electromagnet.setAmount(electromagnetAmountAfterDecrement);
            label.setAmount(labelAmountAfterDecrement);
        } else {
            throw new EquipmentException(" Not enough spare parts for the kit");
        }
    }

    @Override
    public KitOfHydraulicValve get(KitOfHydraulicValveDTO kit) {
        String kitModel = kit.getModel();
        HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kitModel);
        Electromagnet electromagnet = electromagnetsService.getByModelAndVoltage(
                hydraulicValve.getElectromagnetModel().getModel(), kit.getVolt());
        Label label = labelService.getByModel(kitModel);
        return equipment.assembleKit(hydraulicValve, electromagnet, label);
    }

    @Override
    public List<KitOfHydraulicValve> getList(List<KitOfHydraulicValveDTO> kits) {
        List<HydraulicValve> hydraulicValves = hydraulicValveService.getAll();
        List<Electromagnet> electromagnets = electromagnetsService.getAll();
        List<Label> labels = labelService.getAll();

        Map<KitOfHydraulicValveDTO, HydraulicValve> hydraulicValveMap = new HashMap<>();
        for (KitOfHydraulicValveDTO kit : kits) {
            for (HydraulicValve hydraulicValve : hydraulicValves) {
                if (kit.getModel().equals(hydraulicValve.model())) {
                    hydraulicValveMap.putIfAbsent(kit, hydraulicValve);
                }
            }
            checkNotFound(hydraulicValveMap.get(kit) != null
                    , String.format("Hydraulic_Valve model %s", kit.getModel()));
        }

        Map<KitOfHydraulicValveDTO, Label> labelMap = new HashMap<>();
        for (KitOfHydraulicValveDTO kit : kits) {
            for (Label label : labels) {
                if (kit.getModel().equals(label.getModel())) {
                    labelMap.putIfAbsent(kit, label);
                }
            }
            checkNotFound(hydraulicValveMap.get(kit) != null
                    , String.format("Label model %s", kit.getModel()));
        }

        List<KitOfHydraulicValve> kitOfHydraulicValves = new ArrayList<>();
        for (KitOfHydraulicValveDTO kit : kits) {
            for (Electromagnet electromagnet : electromagnets) {
                if (kit.getVolt().equals(electromagnet.getVoltage())) {
                    HydraulicValve hydraulicValveToEquipment = hydraulicValveMap.get(kit);
                    Label label = labelMap.get(kit);
                    if (hydraulicValveToEquipment != null && label != null) {
                        kitOfHydraulicValves.add(equipment.assembleKit(hydraulicValveToEquipment, electromagnet, label));
                    }
                }
            }
        }
        return kitOfHydraulicValves;
    }
}
