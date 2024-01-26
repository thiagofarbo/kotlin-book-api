CREATE SEQUENCE IF NOT exists sq_book
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

CREATE TABLE IF NOT EXISTS book (
    id BIGINT NOT NULL DEFAULT NEXTVAL('sq_book'),
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    publication_year INTEGER NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    number_of_pages INTEGER NOT NULL,
    genre VARCHAR(100) NOT NULL,
    language VARCHAR(50) NOT NULL,
    synopsis VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    available BOOLEAN NOT NULL,
    CONSTRAINT pk_book_id PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT exists sq_customer
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

CREATE TABLE IF NOT EXISTS customer (
    id BIGINT NOT NULL DEFAULT NEXTVAL ('sq_customer'),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    cpf VARCHAR(15) NOT NULL,
    CONSTRAINT pk_customer_id PRIMARY KEY (id)
);

CREATE SEQUENCE IF NOT exists sq_order
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

CREATE TABLE IF NOT EXISTS orders (
    id VARCHAR(255) NOT NULL DEFAULT NEXTVAL ('sq_order'),
    customer_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    order_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(40),
    cpf VARCHAR(15) NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE SEQUENCE IF NOT exists sq_warehouse
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

CREATE TABLE warehouse_books (
    book_id SERIAL PRIMARY KEY,
    in_stock BOOLEAN DEFAULT true,
    quantity_in_stock INT DEFAULT 0,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    publication_year INTEGER NOT NULL,
    publisher VARCHAR(255) NOT NULL,
    number_of_pages INTEGER NOT NULL,
    genre VARCHAR(100) NOT NULL,
    language VARCHAR(50) NOT NULL,
    synopsis VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    available BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO book (title, author, isbn, publication_year, publisher, number_of_pages, genre, language, synopsis, price, available)
VALUES ('Sample Book', 'John Doe', '978-1234567890', 2023, 'Example Publishers', 300, 'Fiction', 'English', 'This is a sample book.', 29.99, TRUE);

INSERT INTO book (title, author, isbn, publication_year, publisher, number_of_pages, genre, language, synopsis, price, available)
VALUES ('Another Book', 'Jane Smith', '978-9876543210', 2022, 'Acme Publishing', 250, 'Mystery', 'English', 'A mysterious book.', 19.99, FALSE);

INSERT INTO customer (id, name, email, cpf)
VALUES (1, 'João Silva', 'joao.silva@example.com', '36632394035');

INSERT INTO customer (id, name, email, cpf)
VALUES (2, 'Maria Souza', 'maria.souza@example.com','52414566191');
