CREATE TABLE IF NOT EXISTS client_entity (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    genre VARCHAR(20),
    age SMALLINT,
    identification VARCHAR(15),
    addres VARCHAR(80) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    password VARCHAR(50) NOT NULL,
    status BOOLEAN
);

CREATE TABLE IF NOT EXISTS account_entity (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(50) NOT NULL,
    account_type VARCHAR(20),
    init_balance INT NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client_entity (id),
    status BOOLEAN
);

CREATE TABLE IF NOT EXISTS movement_entity (
    id SERIAL PRIMARY KEY,
    movement_type VARCHAR(20) NOT NULL,
    value_movement INT,
    balance INT,
    Account_id INT NOT NULL,
    FOREIGN KEY (Account_id) REFERENCES Account_entity (id),
    date_movement TIMESTAMP NOT NULL
);
