package com.gmail.podkutin.dmitry.service;

import com.gmail.podkutin.dmitry.model.Label;

public interface LabelService extends CommonService<Label>  {
    Label getByModel(String model);
}
