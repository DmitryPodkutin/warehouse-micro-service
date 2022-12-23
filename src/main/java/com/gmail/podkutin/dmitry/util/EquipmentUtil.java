package com.gmail.podkutin.dmitry.util;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetModelDTO;
import com.gmail.podkutin.dmitry.model.dto.LabelDTO;
public class EquipmentUtil {

    public static KitOfHydraulicValve equipment(HydraulicValve hydraulicValve, Electromagnet electromagnet, Label label) {
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
