package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.exeption.EquipmentException;
import com.gmail.podkutin.dmitry.exeption.NotFoundException;
import com.gmail.podkutin.dmitry.model.KitOfHydraulicValve;
import com.gmail.podkutin.dmitry.model.dto.KitOfHydraulicValveDTO;
import com.gmail.podkutin.dmitry.service.KitService;
import com.gmail.podkutin.dmitry.service.impl.KitOfHydraulicValveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse/kits-of-hydraulic-valves")
public class KitOfHydraulicValveController {

    private final KitService<KitOfHydraulicValveDTO,KitOfHydraulicValve> kitService;

    public KitOfHydraulicValveController(@Autowired KitOfHydraulicValveServiceImpl kitService) {
        this.kitService = kitService;
    }

    @PreAuthorize("hasAuthority('write')")
    @DeleteMapping()
    ResponseEntity<KitOfHydraulicValve> sale(@RequestBody KitOfHydraulicValveDTO entity) {
        KitOfHydraulicValve kit = kitService.sale(entity);
        return kit != null
                ? new ResponseEntity<>(kit, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping()
    ResponseEntity<KitOfHydraulicValve> get(@RequestBody KitOfHydraulicValveDTO entity) {
        KitOfHydraulicValve kit = kitService.get(entity);
        return kit != null
                ? new ResponseEntity<>(kit, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/filter")
    ResponseEntity<List<KitOfHydraulicValve>> getAllFiltered(@RequestBody List<KitOfHydraulicValveDTO> entity) {
        List<KitOfHydraulicValve> kits = kitService.getAllFiltered(entity);
        return kits != null
                ? new ResponseEntity<>(kits, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}