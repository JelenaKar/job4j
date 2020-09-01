CREATE TABLE users (id bigserial PRIMARY KEY,
                    email varchar(128) NOT NULL UNIQUE,
                    name varchar(128) NOT NULL,
                    password varchar(256) NOT NULL);

CREATE TABLE item (id bigserial PRIMARY KEY,
                    description varchar(256) NOT NULL,
                    user_id bigint,
                    created bigint NOT NULL,
                    done boolean NOT NULL DEFAULT FALSE,
                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL);