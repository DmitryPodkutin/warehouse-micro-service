
insert into electromagnets_types (type) values ('NG6');
insert into electromagnets_types (type) values ('NG10');
insert into electromagnets_types (type) values ('BE6');
insert into electromagnets_types (type) values ('BE10');

insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, type_id) values ('PX06574A1',   10, 4470.00, 1, 100001);
insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, type_id) values ('PX06574A1ОФ', 2,  4920.00, 2, 100001);
insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, type_id) values ('PX06341',     2,  4920.00, 2, 100001);
insert into hydraulic_valves (model, amount, price, number_of_electromagnets_to_complete, type_id) values ('PX06441',     2,  4920.00, 2, 100001);

insert into electromagnets (voltage, amount, price, type_id) values (24,  27, 1470.00, 100001);
insert into electromagnets (voltage, amount, price, type_id) values (110, 27, 1470.00, 100001);
insert into electromagnets (voltage, amount, price, type_id) values (220, 27, 1470.00, 100001);
insert into electromagnets (voltage, amount, price, type_id) values (380, 27,  1470.00, 100001);

insert into labels (model_name, amount, hydraulic_valve_id) values ('PX06574A1', 10,100005);
insert into labels (model_name, amount, hydraulic_valve_id) values ('PX06574A1ОФ', 10,100006);
insert into labels (model_name, amount, hydraulic_valve_id) values ('PX06341', 10,100007);
insert into labels (model_name, amount, hydraulic_valve_id) values ('PX06441', 10,100008);



