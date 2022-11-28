package com.gmail.podkutin.dmitry.test_data;

import com.gmail.podkutin.dmitry.model.Label;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class LabelTestData implements TestData<Label> {

    public static final Label LABEL_1 = new Label(100013, "PX06574A1", 10, 100009);
    public static final Label LABEL_2 = new Label(100014, "PX06574A1ОФ", 10, 100010);
    public static final Label LABEL_3 = new Label(100015, "PX06341", 10, 100011);
    public static final Label LABEL_4 = new Label(100016, "PX06441", 10, 100012);

    @Override
    public Label getFirsEntity() {
        return LABEL_1;
    }

    @Override
    public Label getNew() {
        return Label.builder().modelName("UpdatedTestModel").amount(5).hydraulic_valve_id(100009).build();
    }

    @Override
    public Label getUpdated() {
        return Label.builder().id(LABEL_1.getId()).modelName("UpdatedTestModel")
                .amount(5).hydraulic_valve_id(100009).build();
    }

    @Override
    public List<Label> getAll() {
        return Arrays.asList(LABEL_1, LABEL_2, LABEL_3, LABEL_4);
    }
}
