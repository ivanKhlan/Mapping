CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 200)
);

CREATE TABLE planet (
    id VARCHAR(100) PRIMARY KEY,
    CONSTRAINT check_id_column CHECK (id REGEXP '^[A-Z0-9]+$'),
    name VARCHAR(500) NOT NULL CHECK (LENGTH(name) >= 1 AND LENGTH(name) <= 500)
);

CREATE TABLE ticket (
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    client_id BIGINT,
    from_planet_id VARCHAR(100),
    to_planet_id VARCHAR(100),
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (from_planet_id) REFERENCES planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);