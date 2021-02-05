CREATE TABLE hotel (
  hotel_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  hotel_code CHAR(4) NOT NULL UNIQUE,
  hotel_name VARCHAR(100) NOT NULL,
  status CHAR(1) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);


CREATE TABLE hotel_address (
  address_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  hotel_id BIGINT,
  address_type CHAR(1) NOT NULL,
  address_line_1 VARCHAR(100) NOT NULL,
  address_line_2 VARCHAR(100) NOT NULL,
  address_line_3 VARCHAR(100) NOT NULL,
  address_line_4 VARCHAR(100) NOT NULL,
  state VARCHAR(100) NOT NULL,
  postcode VARCHAR(100) NOT NULL,
  country VARCHAR(100) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);
ALTER TABLE hotel_address ADD FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id);

CREATE TABLE hotel_contact (
  contact_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  hotel_id BIGINT,
  contact_type CHAR(3) NOT NULL,
  contact_value VARCHAR(255) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);
ALTER TABLE hotel_contact ADD FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id);


CREATE TABLE room (
  room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  hotel_id BIGINT,
  room_name CHAR(100),
  room_number VARCHAR(10) NOT NULL,
  room_type CHAR(4) NOT NULL,
  tariff DECIMAL(7,2) NOT NULL,
  status CHAR(1) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);
ALTER TABLE room ADD FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_id);
