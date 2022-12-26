package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.HydraulicValve;

public interface HydraulicValveService extends CommonService<HydraulicValve> {
    HydraulicValve getByModel(String model);
}
