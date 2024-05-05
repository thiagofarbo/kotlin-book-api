CREATE SEQUENCE IF NOT exists sq_coupon
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

CREATE TABLE coupon (
    id BIGINT NOT NULL DEFAULT NEXTVAL ('sq_coupon'),
    code VARCHAR(20) UNIQUE NOT NULL,
    discount DECIMAL(5, 2) NOT NULL,
    description TEXT,
    expiry_date DATE NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT pk_coupon_id PRIMARY KEY (id)
);

INSERT INTO coupon (code, discount, description, expiry_date, active) VALUES
('SUMMER2024', 15.00, 'Summer Sale 2024', '2024-08-31', TRUE),
('FALL2024', 20.00, 'Fall Sale 2024', '2024-11-30', TRUE),
('WINTER2024', 25.00, 'Winter Sale 2024', '2025-02-28', TRUE);



