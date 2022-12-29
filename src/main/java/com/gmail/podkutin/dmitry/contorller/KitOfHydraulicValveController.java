package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.Volt;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import com.gmail.podkutin.dmitry.service.KitService;
import com.gmail.podkutin.dmitry.service.impl.KitOfHydraulicValveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/warehouse/kits-of-hydraulic-valves")
public class KitOfHydraulicValveController {

    private final KitService<KitOfHydraulicValveDTO, KitOfHydraulicValve> kitService;

    public KitOfHydraulicValveController(@Autowired KitOfHydraulicValveServiceImpl kitService) {
        this.kitService = kitService;
    }

    @PreAuthorize("hasAuthority('write')")
    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void save(@RequestBody List<KitOfHydraulicValveDTO> kits) {
        kitService.saveKits(kits);
    }

    @PreAuthorize("hasAuthority('write')")
    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void sale(@RequestParam String model,
              @RequestParam Volt voltage,
              @RequestParam Integer amount) {
        if (amount > 0) {
            kitService.saleKits(new KitOfHydraulicValveDTO(model, voltage, amount));
        }
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping()
    ResponseEntity<List<KitOfHydraulicValve>> getKitsAvailableForEquipment(@RequestBody List<KitOfHydraulicValveDTO> kits) {
        List<KitOfHydraulicValve> result = new ArrayList<>();
        if (kits.size() == 1) {
            result.add(kitService.getKitsAvailableForEquipment(kits.get(0)));
        } else {
            result = kitService.getListKitsAvailableForEquipment(kits);
        }
        return result != null
                ? new ResponseEntity<>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}