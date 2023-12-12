CREATE TABLE users
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    token VARCHAR(100),
    token_expired_at BIGINT,
    unique (token),
    UNIQUE (email)
);

CREATE TABLE contacts
(
    id  INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    email VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100),
    phone VARCHAR(100),
    FOREIGN KEY fk_users_contacts (user_id) references users (id)
);

CREATE TABLE addresses
(
    id          VARCHAR(100) NOT NULL PRIMARY KEY,
    contact_id  INT NOT NULL,
    street      VARCHAR(200),
    city        VARCHAR(100),
    province    VARCHAR(100),
    country     VARCHAR(100) NOT NULL,
    postal_code VARCHAR(10),
    FOREIGN KEY fk_contacts_addresses (contact_id) REFERENCES contacts (id)
);