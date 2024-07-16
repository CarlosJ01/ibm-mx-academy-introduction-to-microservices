# Create a Product

Creates a new product with defined price and currency.

**URL** : `/stock/v1/product/`

**Method** : `POST`

## Request

### **Request Object**

|Parameter|Type|Validation|Decription|Required|
|---|---|---|---|---|
|name|String|None|Product Name|Yes|
|quantity|Long|>0|Number of products of this type (**Default 1**)|No|
|price|BigDecimal|None|Price of the product|Yes|
|currency|String|[*MXN*, *USD*]|This currency is an Enum that must be either: Dolar (**USD**) or Mexican Peso (**MXN**) (**Default MXN**)|No|


**Request example**


```json
{
    "name": "Monitor '26",
    "quantity": 30,
    "price": 8300.98,
    "currency": "MXN"

}
```

## Success Response

**Condition** : If all the validations are correct, then the product is created

**Code** : `201 CREATED`

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
        "price": 8300.98,
        "currency": "MXN",
        "quantity": 30
    }
}
```
## Error Responses

**Condition** : If fields are missed.

**Code** : `400 BAD REQUEST`

**Message** : `Fields are missing`
