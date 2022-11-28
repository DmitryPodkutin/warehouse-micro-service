package com.gmail.podkutin.dmitry.service.impl;

import com.gmail.podkutin.dmitry.model.Electromagnet;
import com.gmail.podkutin.dmitry.repository.ElectromagnetsRepository;
import com.gmail.podkutin.dmitry.test_data.ElectromagnetTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ElectromagnetsServiceImplTest extends AbstractCommonServiceTest<Electromagnet,
        ElectromagnetsRepository,ElectromagnetsServiceImpl, ElectromagnetTestData> {

    public ElectromagnetsServiceImplTest(@Autowired ElectromagnetsServiceImpl service, @Autowired ElectromagnetTestData testData) {
        super(service, testData);
    }
}