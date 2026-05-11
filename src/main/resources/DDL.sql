CREATE DATABASE IF NOT EXISTS bilabonnement;
USE bilabonnement;

CREATE TABLE customer (
    customer_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    phone VARCHAR(250),
    email VARCHAR(250),
    PRIMARY KEY (customer_id)
);

CREATE TABLE employee (
    employee_id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL,
    username VARCHAR(250) NOT NULL UNIQUE,
    password VARCHAR(250) NOT NULL,
    role ENUM('DATA_REGISTRATION', 'DAMAGE_AND_REPAIR', 'BUSINESS_DEVELOPER', 'ADMIN') NOT NULL,
    PRIMARY KEY (employee_id)
);

CREATE TABLE car (
    car_id BIGINT NOT NULL AUTO_INCREMENT,
    stel_number VARCHAR(250) NOT NULL UNIQUE,
    model VARCHAR(250),
    maker VARCHAR(250),
    colour VARCHAR(250),
    year INT,
    status ENUM('AWAITING_REGISTRATION', 'AVAILABLE', 'RENTED', 'RETURNED', 'UNDER_REPAIR') NOT NULL,
    PRIMARY KEY (car_id)
);

CREATE TABLE damage_report (
    report_id BIGINT NOT NULL AUTO_INCREMENT,
    car_id BIGINT NOT NULL,
    report_date DATE NOT NULL,
    PRIMARY KEY (report_id),
    FOREIGN KEY (car_id) REFERENCES car(car_id)
);

CREATE TABLE damage_item (
    item_id BIGINT NOT NULL AUTO_INCREMENT,
    report_id BIGINT NOT NULL,
    description VARCHAR(250),
    price DECIMAL(10,2),
    PRIMARY KEY (item_id),
    FOREIGN KEY (report_id) REFERENCES damage_report(report_id)
);

CREATE TABLE rental_agreement (
    agreement_id BIGINT NOT NULL AUTO_INCREMENT,
    car_id BIGINT NOT NULL,
    customer_id BIGINT NOT NULL,
    report_id BIGINT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    location ENUM('HQ', 'DS_DEALER') NOT NULL,
    subscription_type ENUM('LIMITED', 'UNLIMITED') NOT NULL,
    PRIMARY KEY (agreement_id),
    FOREIGN KEY (car_id) REFERENCES car(car_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
    FOREIGN KEY (report_id) REFERENCES damage_report(report_id)
);

CREATE TABLE car_registration (
    registration_id BIGINT NOT NULL AUTO_INCREMENT,
    agreement_id BIGINT NOT NULL,
    leasing_code VARCHAR(250),
    irk_code VARCHAR(250),
    plate_number VARCHAR(250),
    PRIMARY KEY (registration_id),
    FOREIGN KEY (agreement_id) REFERENCES rental_agreement(agreement_id)
);