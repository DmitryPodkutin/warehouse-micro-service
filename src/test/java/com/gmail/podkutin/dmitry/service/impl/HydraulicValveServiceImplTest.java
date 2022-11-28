package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.model.HydraulicValve;
import com.gmail.podkutin.dmitry.repository.HydraulicValvesRepository;
import com.gmail.podkutin.dmitry.test_data.HydraulicValveTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HydraulicValveServiceImplTest   extends AbstractCommonServiceTest<HydraulicValve,
        HydraulicValvesRepository,HydraulicValveServiceImpl, HydraulicValveTestData>  {


    public HydraulicValveServiceImplTest(@Autowired HydraulicValveServiceImpl service
            ,@Autowired HydraulicValveTestData testData) {
        super(service, testData);
    }
}