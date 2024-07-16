# GET a Sales Order by Client Name

Get sales order detail by the client name.

**URL** : `/sales/v1/order?client={client}`

**Method** : `GET`

## Success Responses

**Condition** : The order exists and the name matches completely and returns the order details.

**Code** : `200 OK`

**Content Type** : `application/json` 

### Headers

**Currency**: (Optional) Currency of the total that returns the [Order Object](#order-object). Default value is: **MXN**

### Response Object

### **Orders Object**
|Parameter|Type|Validation|Description|
|---|---|---|---|
|orders|List\<[Order](#order-object)>|None|List of orders that match the name provided by the **Query Param**

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
    "orders": [
        {
            "id": "d6507aa1-b943-4b5e-bbe4-231bfc5cbb63",
            "client": "Raul Montemayor",
            "products": [
                {
                    "id":"a7db8457-3858-443d-a09e-839e9a5aaf51",
                    "name": "keyboard",
                    "price": 2500.00,
                    "currency": "MXN",
                    "quantity": 1
                }
            ],
            "total":2500.00,
            "currency":"MXN"
        },
        {
            "id": "bcd2f670-d20f-472d-999e-d6ab8bdc0290",
            "client": "Raul",
            "products": [
                {
                    "id":"ab4db89f-39b8-4802-9ad3-cd7212b1e3bb",
                    "name": "light",
                    "price": 1000.00,
                    "currency": "MXN",
                    "quantity": 3
                },
                {
                    "id":"9ecd5ab9-c529-411c-b04e-354350f8a640",
                    "name": "mouse pad",
                    "price": 200.00,
                    "currency": "MXN",
                    "quantity": 1
                }
            ],
            "total":3200.00,
            "currency":"MXN"
        }
    ]
}
```

## Error Responses

**Condition** : If the name doesn't exists or has not orders and returns not found error.

**Code** : `404 NOT FOUND`

**Message** : `Order with client {name} not found`