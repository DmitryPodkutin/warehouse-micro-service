package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.service.impl.ElectromagnetsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse/electromagnets")
public class ElectromagnetRestController extends AbstractController<Electromagnet, ElectromagnetsServiceImpl> {
    public ElectromagnetRestController(@Autowired ElectromagnetsServiceImpl service) {
        super(service);
    }
}
