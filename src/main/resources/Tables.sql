DROP TABLE Product IF EXISTS;
DROP TABLE Seller IF EXISTS;

CREATE TABLE Seller (
    seller_name varchar(255) primary key
  );


  CREATE TABLE Product (
      product_id BIGINT PRIMARY KEY,
      product_name varchar(255),
      price DOUBLE,
      seller_name varchar(255) references Seller(seller_name)
    );
