package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.service.impl.HydraulicValveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse/hydraulic-valves")
public class HydraulicValveRestController extends AbstractController<HydraulicValve, HydraulicValveServiceImpl> {
    protected HydraulicValveRestController(@Autowired HydraulicValveServiceImpl service) {
        super(service);
    }
}
