HTTP project
Two models:
Product
- Product Id (must be unique)
- Product Name
- Price
- Seller
  Seller
- Seller Name (must be unique)

Two separate Service classes one for Seller and Product:

Create/Read functionality on Seller

GET /seller/
- all sellers
  POST  /seller/
- Seller Names must be non-null and unique

CRUD (create, Read, Update, Delete) functionality on Product
GET /product/
- All products
- 
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
Logging within service classes
