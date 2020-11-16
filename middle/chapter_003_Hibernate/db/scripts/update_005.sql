CREATE SEQUENCE seller_id_seq;
CREATE TABLE seller (id integer DEFAULT nextval('seller_id_seq') PRIMARY KEY,
            name varchar (256) NOT NULL,
            address varchar(512),
            email varchar(128) NOT NULL,
            phone varchar(128) NOT NULL,
            regdate bigint NOT NULL,
            password varchar(1024) NOT NULL,
            CONSTRAINT chk_phone CHECK (phone SIMILAR TO '_+[0-9]{11,15}' ESCAPE '_')
        );
ALTER SEQUENCE seller_id_seq OWNED BY seller.id;

CREATE SEQUENCE make_id_seq;
CREATE TABLE make (id integer DEFAULT nextval('make_id_seq') PRIMARY KEY,
                   name varchar(256) NOT NULL
);
ALTER SEQUENCE make_id_seq OWNED BY make.id;

CREATE SEQUENCE model_id_seq;
CREATE TABLE model (id integer DEFAULT nextval('model_id_seq') PRIMARY KEY,
                    name varchar(256) NOT NULL,
                    make_id int NOT NULL references make(id) ON DELETE CASCADE
);
ALTER SEQUENCE model_id_seq OWNED BY model.id;

CREATE SEQUENCE body_id_seq;
CREATE TABLE bodystyle (id integer DEFAULT nextval('body_id_seq') PRIMARY KEY,
            name varchar(256) NOT NULL
        );
ALTER SEQUENCE body_id_seq OWNED BY bodystyle.id;

CREATE SEQUENCE engine_type_id_seq;
CREATE TABLE engine_type (id integer DEFAULT nextval('engine_type_id_seq') PRIMARY KEY,
            name varchar(256) NOT NULL
        );
ALTER SEQUENCE engine_type_id_seq OWNED BY engine_type.id;

CREATE SEQUENCE driveunit_id_seq;
CREATE TABLE drive_unit (id integer DEFAULT nextval('driveunit_id_seq') PRIMARY KEY,
            name varchar(256) NOT NULL
        );
ALTER SEQUENCE driveunit_id_seq OWNED BY drive_unit.id;

CREATE SEQUENCE transmission_id_seq;
CREATE TABLE transmission (id integer DEFAULT nextval('transmission_id_seq') PRIMARY KEY,
            name varchar(256) NOT NULL
        );
ALTER SEQUENCE transmission_id_seq OWNED BY transmission.id;

CREATE SEQUENCE auto_id_seq;
CREATE TABLE auto (id integer DEFAULT nextval('auto_id_seq') PRIMARY KEY,
            make_id integer NOT NULL references make(id),
            model_id integer NOT NULL references model(id),
            manufactured integer NOT NULL,
            body_id integer NOT NULL references bodystyle(id),
            doors integer,
            engine_id integer NOT NULL references engine_type(id),
            driveunit_id integer NOT NULL references drive_unit(id),
            transmission_id integer NOT NULL references transmission(id),
            modification varchar(256),
            leftwheel boolean NOT NULL DEFAULT true,
            mileage integer NOT NULL,
            isbroken boolean NOT NULL DEFAULT false,
            color varchar(64) NOT NULL,
            owner_number integer NOT NULL
);
ALTER SEQUENCE auto_id_seq  OWNED BY auto.id;

CREATE SEQUENCE ad_id_seq;
CREATE TABLE ad (id integer DEFAULT nextval('ad_id_seq') PRIMARY KEY,
            auto_id integer NOT NULL references auto(id),
            price integer NOT NULL,
            seller_id integer NOT NULL references seller(id),
            created bigint,
            issold boolean NOT NULL DEFAULT false
         );
ALTER SEQUENCE ad_id_seq OWNED BY ad.id;

CREATE SEQUENCE folder_id_seq;
CREATE TABLE folder (id integer DEFAULT nextval('folder_id_seq') PRIMARY KEY,
                     name varchar(2048),
                     ad_id integer references ad(id)
);
ALTER SEQUENCE folder_id_seq OWNED BY folder.id;

CREATE SEQUENCE photo_id_seq;
CREATE TABLE photo (id integer DEFAULT nextval('photo_id_seq') PRIMARY KEY,
            name varchar(2048),
            folder_id integer references folder(id) ON DELETE CASCADE
         );
ALTER SEQUENCE photo_id_seq OWNED BY photo.id;