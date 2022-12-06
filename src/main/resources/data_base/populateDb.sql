
insert into electromagnets_models (model) values ('NG6');
insert into electromagnets_models (model) values ('NG10');
insert into electromagnets_models (model) values ('BE6');
insert into electromagnets_models (model) values ('BE10');

insert into electromagnets (model_id, voltage, amount, price) values (100001, 'VOLT_24',  10, 1470.00);
insert into electromagnets (model_id, voltage, amount, price) values (100001, 'VOLT_110', 10, 1470.00);
insert into electromagnets (model_id, voltage, amount, price) values (100001, 'VOLT_220', 10, 1470.00);
insert into electromagnets (model_id, voltage, amount, price) values (100001, 'VOLT_380', 10,  1470.00);

insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, model_id) values ('РХ06574А1',   10, 4470.00, 1, 100001);
insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, model_id) values ('РХ06574А1ОФ', 2,  4920.00, 2, 100001);
insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, model_id) values ('РХ06341',     2,  4920.00, 2, 100001);
insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, model_id) values ('РХ06441',     2,  4920.00, 2, 100001);

insert into labels (model_name, amount, hydraulic_valve_id) values ('РХ06574А1', 10,100009);
insert into labels (model_name, amount, hydraulic_valve_id) values ('РХ06574А1ОФ', 10,100010);
insert into labels (model_name, amount, hydraulic_valve_id) values ('РХ06341', 10,100011);
insert into labels (model_name, amount, hydraulic_valve_id) values ('РХ06441', 10,100012);

insert into users (email, firs_name, last_name, password, role, status) values ('admin@mail.com', 'Dmitry','Podkutin','$2a$12$fjW87B.zmCSzXf6dFOuAR.Rx4M6FJuvkPWp6tZJFRrlUzjuq//fNW','ADMIN','ACTIVE');
insert into users (email, firs_name, last_name, password, role, status) values ('user@mail.com', 'Ivonov','Ivan','$2a$12$AcxYG71za7M5L1t/huYxpOVhiL2ZsQU3yYSvcyJBQ.ocOtzT7S8kG','USER','ACTIVE');



