package com.gmail.podkutin.dmitry.contorller;

import com.gmail.podkutin.dmitry.model.Label;
import com.gmail.podkutin.dmitry.service.impl.LabelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouse/labels")
public class LabelRestController extends AbstractController<Label, LabelServiceImpl>{
    protected LabelRestController(@Autowired LabelServiceImpl service) {
        super(service);
    }
}
