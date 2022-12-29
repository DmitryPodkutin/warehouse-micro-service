package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetModelDTO;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import com.gmail.podkutin.dmitry.model.dto.LabelDTO;
import com.gmail.podkutin.dmitry.service.ElectromagnetsService;
import com.gmail.podkutin.dmitry.service.HydraulicValveService;
import com.gmail.podkutin.dmitry.service.KitService;
import com.gmail.podkutin.dmitry.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

import static com.gmail.podkutin.dmitry.util.ValidationUtil.checkNotFound;

@Service
public class KitOfHydraulicValveServiceImpl implements KitService<KitOfHydraulicValveDTO, KitOfHydraulicValve> {

    private final HydraulicValveService hydraulicValveService;

    private final ElectromagnetsService electromagnetsService;

    private final LabelService labelService;

    public KitOfHydraulicValveServiceImpl(@Autowired HydraulicValveServiceImpl hydraulicValveService,
                                          @Autowired ElectromagnetsServiceImpl electromagnetsService,
                                          @Autowired LabelServiceImpl labelService) {
        this.hydraulicValveService = hydraulicValveService;
        this.electromagnetsService = electromagnetsService;
        this.labelService = labelService;
    }

    @Override
    @Transactional
    public void saveKits(List<KitOfHydraulicValveDTO> kits) {
        for (KitOfHydraulicValveDTO kit : kits) {
            HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kit.getModel());
            Electromagnet electromagnet = electromagnetsService.getByModelAndVoltage(hydraulicValve.getElectromagnetModel()
                    .getModel(), kit.getVolt());
            Label label = labelService.getByModel(kit.getModel());
            Integer kitAmount = kit.getAmount();
            hydraulicValve.setAmount(hydraulicValve.getAmount() + kitAmount);
            electromagnet.setAmount(electromagnet.getAmount() + hydraulicValve.getNumberOfElectromagnetsToComplete()
                    * kitAmount);
            label.setAmount(label.getAmount() + kitAmount);
        }
    }

    @Override
    @Transactional
    public KitOfHydraulicValve saleKits(KitOfHydraulicValveDTO kit) {
        HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kit.getModel());
        Electromagnet electromagnet = electromagnetsService.getByModelAndVoltage(hydraulicValve.getElectromagnetModel()
                .getModel(), kit.getVolt());
        Label label = labelService.getByModel(kit.getModel());
        int kitsQuantity = hydraulicValve.getAmount();
        int labelQuantity = label.getAmount();
        int quantityAvailableElectromagnets = electromagnet.getAmount()
                / hydraulicValve.getNumberOfElectromagnetsToComplete();
        int amount = kit.getAmount();
        if (kitsQuantity >= amount && labelQuantity >= amount && quantityAvailableElectromagnets >= amount) {
            return decrementElementsAndCreateHydraulicValveKit(hydraulicValve, electromagnet, label, amount);
        } else {
            throw new EquipmentException(" Not enough spare parts for the kit");
        }
    }

    @Override
    public KitOfHydraulicValve getKitsAvailableForEquipment(KitOfHydraulicValveDTO kit) {
        HydraulicValve hydraulicValve = hydraulicValveService.getByModel(kit.getModel());
        Electromagnet electromagnet = electromagnetsService.getByModelAndVoltage(hydraulicValve.getElectromagnetModel()
                .getModel(), kit.getVolt());
        Label label = labelService.getByModel(kit.getModel());
        return decrementElementsAndCreateHydraulicValveKit(hydraulicValve, electromagnet, label
                , 0);
    }

    @Override
    public List<KitOfHydraulicValve> getListKitsAvailableForEquipment(List<KitOfHydraulicValveDTO> kits) {
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
                        kitOfHydraulicValves.add(decrementElementsAndCreateHydraulicValveKit(hydraulicValveMap.get(kit), electromagnet, label
                                , 0));
                    }
                }
            }
        }
        return kitOfHydraulicValves;
    }

    private KitOfHydraulicValve decrementElementsAndCreateHydraulicValveKit(HydraulicValve hydraulicValve
            , Electromagnet electromagnet, Label label, int amount) {
        if (amount <= 0) {
            int kitsQuantity = hydraulicValve.getAmount();
            int labelQuantity = label.getAmount();
            int numberOfElectromagnetsToComplete = hydraulicValve.getNumberOfElectromagnetsToComplete();
            int quantityAvailableElectromagnets = electromagnet.getAmount() / numberOfElectromagnetsToComplete;
            if (kitsQuantity <= labelQuantity && kitsQuantity <= quantityAvailableElectromagnets) {
                amount = kitsQuantity;
            } else if (labelQuantity <= kitsQuantity && labelQuantity <= quantityAvailableElectromagnets) {
                amount = labelQuantity;
            } else {
                amount = quantityAvailableElectromagnets;
            }
        }
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