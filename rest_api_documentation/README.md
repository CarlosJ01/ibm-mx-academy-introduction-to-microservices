# Stock Microservice:

Create a `Stock microservice` that manages the information of products, such as name, price or quantity of the stock.

This microservice will have the following enpoints:

## REST Endpoints

* [Create Product](stock/createProduct.md) : `POST /stock/v1/product/`
* [Get Product by Id](stock/getProductById.md) : `GET /stock/v1/product/{productId}`
* [Update Product](stock/getProductById.md) : `PUT/PATCH /stock/v1/product/{productId}`

The database will be **H2**, and the service will run at port: `:8080`
Please make an initial load of products into the database, using `imports.sql`


# Sales Microservice

Create a `Sales microservice` that manages the sales of products with a sales order.

This microservice will have the following endpoints:

## REST Endpoints

* [Create Order](sales/createOrder.md) : `POST /sales/v1/order/`
* [Get Order by Id](sales/getOrderbyId.md) : `GET /sales/v1/order/{orderId}`
* [Get Order by Name](sales/getOrderByName.md) : `/sales/v1/order?client={client}`
