CREATE TABLE hotel (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  code CHAR(4) NOT NULL UNIQUE,
  name VARCHAR(100) NOT NULL,
  status CHAR(1) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);


CREATE TABLE hotel_address (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  hotel_id BIGINT,
  type CHAR(1) NOT NULL,
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
ALTER TABLE hotel_address ADD FOREIGN KEY (hotel_id) REFERENCES hotel(id);

CREATE TABLE hotel_contact (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  hotel_id BIGINT,
  contact_type CHAR(3) NOT NULL,
  contact_value VARCHAR(255) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);
ALTER TABLE hotel_contact ADD FOREIGN KEY (hotel_id) REFERENCES hotel(id);


CREATE TABLE room (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  hotel_id BIGINT,
  name CHAR(100),
  number VARCHAR(10) NOT NULL,
  type CHAR(4) NOT NULL,
  tariff DECIMAL(7,2) NOT NULL,
  status CHAR(1) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);
ALTER TABLE room ADD FOREIGN KEY (hotel_id) REFERENCES hotel(id);
ALTER TABLE room ADD CONSTRAINT uq_room UNIQUE(hotel_id, number);

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  mobile VARCHAR(13) NOT NULL,
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);

CREATE TABLE room_reservation (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  room_id BIGINT,
  user_id BIGINT,
  status CHAR(1) NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  email VARCHAR(255),
  mobile VARCHAR(13),
  ext_reference VARCHAR(255),
  agent VARCHAR(255),
  created_at VARCHAR(50) NOT NULL,
  created_by VARCHAR(100) NOT NULL,
  modified_at VARCHAR(50) not null,
  modified_by VARCHAR(100) NOT NULL
);
ALTER TABLE room_reservation ADD FOREIGN KEY (room_id) REFERENCES room(id);
ALTER TABLE room_reservation ADD FOREIGN KEY (user_id) REFERENCES user(id);
