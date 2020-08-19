CREATE TABLE item (id bigserial PRIMARY KEY,
                    description varchar(256) NOT NULL,
                    login varchar(64) NOT NULL,
                    created bigint NOT NULL,
                    done boolean NOT NULL DEFAULT FALSE);