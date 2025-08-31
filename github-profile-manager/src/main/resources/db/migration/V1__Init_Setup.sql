 CREATE TABLE roles (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       login VARCHAR(255) NOT NULL UNIQUE,
                       url VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
                            user_id BIGINT NOT NULL,
                            role_id BIGINT NOT NULL,
                            PRIMARY KEY (user_id, role_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (role_id) REFERENCES roles(id)
);