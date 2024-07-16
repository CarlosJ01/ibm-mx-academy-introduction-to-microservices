# Updates a Product

Updates a product's details.

**URL** : `/stock/v1/product/{productId}`

**Method** : `PUT`| `PATCH`

## Request

### **Request Object**

|Parameter|Type|Validation|Decription|Required|
|---|---|---|---|---|
|name|String|None|Product Name|No|
|quantity|Long|>0|Number of products of this type|No|
|price|BigDecimal|None|Price of the product|No|
|currency|String|[*MXN*, *USD*]|This currency is an Enum that must be either: Dolar (**USD**) or Mexican Peso (**MXN**)|No|


**Request example**

```json
{
    "quantity": 28,
    "price": 8200.98
}
```

## Success Response

**Condition** : If all the validations are correct, then the sales order is created

**Code** : `200 OK`


### Response Object

### **Product Object**
|Parameter|Type|Validation|Decription|
|---|---|---|---|
|id|String| UUID |Auto generated Id, using UUID standard|
|name|String|None|Product Name|
|price|BigDecimal|None|Price of the product|
|currency|String|[*MXN*, *USD*]|This currency is an Enum that must be either: Dolar (**USD**) or Mexican Peso (**MXN**)|
|quantity|Long|>0|Number of products of this type|

```json
{
    "products": {
        "id":"37ed6bed-4648-4d01-90de-0ea36b2c365b",
        "name": "Monitor '26",
        "price": 8200.98,
        "currency": "MXN",
        "quantity": 28
    }
}
```
## Error Responses

**Condition** : If fields are not valid.

**Code** : `400 BAD REQUEST`
