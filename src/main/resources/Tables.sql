DROP TABLE Product IF EXISTS;
DROP TABLE Seller IF EXISTS;

CREATE TABLE Seller (
--add seller_id
    seller_id BIGINT PRIMARY KEY,
    seller_name varchar(255)
  );


  CREATE TABLE Product (
      product_id BIGINT PRIMARY KEY,
      product_name varchar(255),
      price DOUBLE,
--Remove seller_name from Product
--seller_name varchar(255) references Seller(seller_name)
      seller_id BIGINT references Seller(seller_id)
    );
