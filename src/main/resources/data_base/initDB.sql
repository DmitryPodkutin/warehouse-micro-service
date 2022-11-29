       drop table  IF EXISTS labels, hydraulic_valves, electromagnets,electromagnets_types;
       drop sequence IF EXISTS GLOBAL_SEQ;

       create sequence GLOBAL_SEQ AS INTEGER START with 100001;

      create TABLE electromagnets_types
    (
       id                      INTEGER       DEFAULT nextval('GLOBAL_SEQ') PRIMARY KEY,
       type                    VARCHAR(10)   NOT NULL
    );
    create unique index store_unique_type_id_idx on electromagnets_types (type);

       create TABLE hydraulic_valves
    (
       id                                       INTEGER                         NOT NULL DEFAULT nextval('GLOBAL_SEQ') PRIMARY KEY,
       model                                    VARCHAR(255)                    NOT NULL,
       amount                                   INTEGER CHECK (amount >= 0)      NOT NULL,
       price                                    DECIMAL(10,2) CHECK (price >= 0) NOT NULL,
       number_of_electromagnets_to_complete     INTEGER CHECK (number_of_electromagnets_to_complete > 0 AND number_of_electromagnets_to_complete <= 2)  NOT NULL,
       type_id                                  INTEGER                         NOT NULL,
       FOREIGN KEY (type_id) REFERENCES electromagnets_types (id)
    );
   create unique index store_unique_model_idx on hydraulic_valves (model);

     create TABLE electromagnets
    (
       id                      INTEGER                           DEFAULT nextval('GLOBAL_SEQ'),
       voltage                 INTEGER                           NOT NULL,
       amount                  INTEGER CHECK (amount >= 0)       NOT NULL,
       price                   DECIMAL(10,2)  CHECK (price >= 0) NOT NULL,
       type_id                 INTEGER                           NOT NULL,
       FOREIGN KEY (type_id) REFERENCES electromagnets_types (id)
    );
    create unique index store_unique_voltage_id_idx on electromagnets (voltage,type_id);

  create TABLE labels
    (
       id                      INTEGER                       DEFAULT nextval('GLOBAL_SEQ'),
       model_name              VARCHAR(255)                  NOT NULL,
       amount                  INTEGER CHECK (amount >= 0)   NOT NULL,
       hydraulic_valve_id      INTEGER                       NOT NULL,
       FOREIGN KEY (hydraulic_valve_id) REFERENCES hydraulic_valves (id)
    );
    create unique index store_unique_modelName_idx on labels (model_name);

  create TABLE users
    (
      id                     INTEGER                       DEFAULT nextval('GLOBAL_SEQ'),
      email                  VARCHAR(255)                  NOT NULL,
      firs_name              VARCHAR(50)                   NOT NULL,
      last_name              VARCHAR(100)                  NOT NULL,
      password               VARCHAR(255)                  NOT NULL,
      role                   VARCHAR(20) DEFAULT 'USER'    NOT NULL,
      status                 VARCHAR(20) DEFAULT 'ACTIV'   NOT NULL
    );
    create unique index store_unique_email_idx on users (email);