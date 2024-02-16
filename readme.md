// Updated Requirements to include SQL
// DUE on 2/26/24

HTTP and SQL project
Two models/ Tables:
Product
- Product Id (must be unique)
- Product Name
- Price
- Seller

  Seller
- Seller ID
- Seller Name (must be unique)

Naturally, the two tables must be related by a foreign key.
It is OK for the project to reset the database on every run of the API.
  If you would like your project to persist between runs, you cna just comment our you .sql file contents.
You may use the ConnectionSingleton, tables.sql, and pom-file provided in the h2 template repo to start up the SQL integration

On occasion, h2 files can get corrupted, If you suspect that this has happened, there is no harm in just deleting h2 folder and 
allowing h2 to regenerate its files.

Two separate Service classes one for Seller and Product:

Create/Read functionality on Seller

GET /seller/
- all sellers
  POST  /seller/
- Seller Names must be non-null and unique

CRUD (create, Read, Update, Delete) functionality on Product
      GET /product/
- All products
  GET /product/{id}
- Get a single product
  - We should get a 404 error when we try to access a non-existed product.
    - 
      POST /product/
- Add a single product
- Product ids should be non-null and unique
- product names should be non-null
- price should be over 0
- Seller name should refer to an actually existing seller
 
    PUT /product/{id}
- Update a single product
- product names should be non-null
- price should be over 0
- Seller name should refer to an actually existing seller

    DELETE /product/{id}
- Delete a single product
- Delete should always return 200, regardless of if the item existed at the start or not. This is convention.

Unit testing of service classes
  - if you like, you can mock the DAO with mockito.
  - otherwise just resetting the DB between every test will be fine.
Logging within service classes
Readme file

Javalin