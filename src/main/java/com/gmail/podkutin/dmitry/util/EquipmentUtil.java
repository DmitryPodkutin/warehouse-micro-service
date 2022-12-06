package com.gmail.podkutin.dmitry.util;

import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;

public class EquipmentUtil {

    public static KitOfHydraulicValve equipment(HydraulicValve hydraulicValve, ElectromagnetDTO electromagnet) {
        int kitsQuantity = hydraulicValve.getAmount();
        int numberOfElectromagnetsToComplete = hydraulicValve.getNumberOfElectromagnetsToComplete();
        int quantityAvailableElectromagnets = electromagnet.getAmount() / numberOfElectromagnetsToComplete;

        KitOfHydraulicValve kitOfHydraulicValve = KitOfHydraulicValve.builder()
                .model(hydraulicValve.getModel())
                .amount(0)
                .price(hydraulicValve.getPrice())
                .electromagnet(new ElectromagnetDTO(electromagnet)).build();

        ElectromagnetDTO electromagnetDTO = kitOfHydraulicValve.getElectromagnet();
        if (kitsQuantity <= quantityAvailableElectromagnets) {
            electromagnetDTO.setAmount(kitsQuantity * numberOfElectromagnetsToComplete);
            kitOfHydraulicValve.setAmount(kitsQuantity);
        } else {
            electromagnetDTO.setAmount(quantityAvailableElectromagnets * numberOfElectromagnetsToComplete);
            kitOfHydraulicValve.setAmount(quantityAvailableElectromagnets);
        }
        return kitOfHydraulicValve;
    }
}
