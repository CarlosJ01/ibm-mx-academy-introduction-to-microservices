# GET Product by Id

Get Product detail by an id.

**URL** : `/stock/v1/product/{productId}`

**Method** : `GET`

## Success Responses

**Condition** : The product exists and returns the product details.

**Code** : `200 OK`

**Content Type** : `application/json` 

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
    "product": {
            "id":"3702b220-1d29-457d-b0be-f2cc6a6a2ac8",
            "name": "headset",
            "price": 100.00,
            "currency": "USD",
            "quantity": 1
        }
}
```

## Error Responses

**Condition** : If the product doesn't exists and returns a not found error

**Code** : `404 NOT_FOUND`

**Message** : `Product with {id} not found`