# Create a Sales Order

Creates a Sales order asociated with a client name, and a list of products with the quantity to buy.

**URL** : `/sales/v1/order/`

**Method** : `POST`

## Validations

Must use [Stock API](../stock/getProductById.md#get-product-by-id) to validate:

* Which is the price of each product
* If all the products of the provided list have enough stock
    * If all items have enough stock, then calculate the **Total** of the order
    * If there is a product that does not have enough stock, then return [Not Enough Stock Error](#not-enough-stock)

## Request

### **Request Object**
|Parameter|Type|Validation|Decription|Required|
|---|---|---|---|---|
|client|String|None|Client name|Yes|
|products|List\<[Product](#product-object)>| Match object type| List of products of the order|Yes|

### **Product Object**
|Parameter|Type|Validation|Decription|Required|
|---|---|---|---|---|
|name|String|None|Product Name|Yes|
|quantity|Long|>0|Number of products of this type|Yes|



**Request example**


```json
{
    "client": "Gabriel Castillo",
    "products": [
        {
            "id":"a7db8457-3858-443d-a09e-839e9a5aaf51",
            "quantity": 2
        }
    ]
}
```

## Success Response

**Condition** : If all the validations are correct, then the sales order is created

**Code** : `201 CREATED`


### Response Object

### **Order Object**
|Parameter|Type|Validation|Description|
|---|---|---|---|
|id|String| UUID |Auto generated Id, using UUID standard|
|client|String|None|Client name|
|products|List\<[Product](#product-object)>| Match object type| List of products of the order|
|total|BigDecimal|**Default is MXN**|**Calculated** Total sum of the products, and this currency must match Header [Currency](#headers) |
|currency|String|**Default is MXN** [*MXN*, *USD*]|This currency is an Enum that must be either: Dolar (**USD**) or Mexican Peso (**MXN**)

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
    "order": {
        "id": "70454cc0-35d7-4088-8eff-4c84b200fd59",
        "client": "Gabriel Castillo",
        "products": [
            {
                "id":"a7db8457-3858-443d-a09e-839e9a5aaf51",
                "name": "keyboard",
                "price": 2500.00,
                "currency": "MXN",
                "quantity": 2
            }
        ],
        "total":5000.00,
        "currency":"MXN"
    }  
}
```
## Error Responses

### Not Enough Stock

**Condition** : If Stock of given product is out of bounds.

**Code** : `406 NOT ACCEPTABLE`

**Message** : `Stock is not enough for this product {productId}`

### Or

**Condition** : If fields are missed.

**Code** : `400 BAD REQUEST`

**Message** : `Fields are missing`
