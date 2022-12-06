package com.gmail.podkutin.dmitry.util;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.model.ElectromagnetModel;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetDTO;
import com.gmail.podkutin.dmitry.model.dto.ElectromagnetModelDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ElectromagnetUtil {


    public static ElectromagnetDTO toDto(Electromagnet electromagnet) {
        return new ElectromagnetDTO(toDto(electromagnet.getElectromagnetModel())
                , electromagnet.getVoltage(), electromagnet.getAmount());
    }

    public static List<ElectromagnetDTO> toDto(List<Electromagnet> electromagnets) {
        return electromagnets.stream().map(electromagnet -> new ElectromagnetDTO(toDto(electromagnet.getElectromagnetModel())
                , electromagnet.getVoltage(), electromagnet.getAmount())).collect(Collectors.toList());
    }


    public static ElectromagnetModelDTO toDto(ElectromagnetModel electromagnetModel) {
        return new ElectromagnetModelDTO(electromagnetModel.getModel());
    }


}
