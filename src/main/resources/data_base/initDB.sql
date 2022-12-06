       drop table  IF EXISTS labels, hydraulic_valves, electromagnets,electromagnets_models,users;
       drop sequence IF EXISTS GLOBAL_SEQ;

    create sequence GLOBAL_SEQ AS INTEGER START with 100001;

    create TABLE electromagnets_models
    (
       id                      INTEGER       DEFAULT nextval('GLOBAL_SEQ') PRIMARY KEY,
       model                   VARCHAR(10)   NOT NULL
    );
    create unique index store_unique_type_id_idx on electromagnets_models (model);

     create TABLE electromagnets
    (
       id                      INTEGER                           DEFAULT nextval('GLOBAL_SEQ'),
       model_id                INTEGER                           NOT NULL,
       voltage                 VARCHAR                           NOT NULL,
       amount                  INTEGER CHECK (amount >= 0)       NOT NULL,
       price                   DECIMAL(10,2)  CHECK (price >= 0) NOT NULL,

       FOREIGN KEY (model_id) REFERENCES electromagnets_models (id)
    );
    create unique index store_unique_voltage_id_idx on electromagnets (voltage,model_id);

  create TABLE hydraulic_valves
    (
       id                                       INTEGER                          NOT NULL DEFAULT nextval('GLOBAL_SEQ') PRIMARY KEY,
       model                                    VARCHAR(255)                     NOT NULL,
       amount                                   INTEGER CHECK (amount >= 0)      NOT NULL,
       price                                    DECIMAL(10,2) CHECK (price >= 0) NOT NULL,
       number_of_electromagnets_to_complete     INTEGER CHECK (number_of_electromagnets_to_complete > 0 AND number_of_electromagnets_to_complete <= 2)  NOT NULL,
       model_id                                 INTEGER                         NOT NULL,
       FOREIGN KEY (model_id) REFERENCES electromagnets_models (id)
    );
   create unique index store_unique_model_idx on hydraulic_valves (model);

     create TABLE labels
    (
       id                      INTEGER                       DEFAULT nextval('GLOBAL_SEQ'),
       model_name              VARCHAR(255)                  NOT NULL,
       amount                  INTEGER CHECK (amount >= 0)   NOT NULL,
       hydraulic_valve_id      INTEGER                       NOT NULL,
       FOREIGN KEY (hydraulic_valve_id) REFERENCES hydraulic_valves (id)
    );
    create unique index store_unique_modelName_idx on labels (hydraulic_valve_id);

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