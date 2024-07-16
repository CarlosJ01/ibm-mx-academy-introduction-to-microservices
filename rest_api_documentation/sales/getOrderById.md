# GET a Sales Order by Id

Get sales order detail by an id.

**URL** : `/sales/v1/order/{orderId}`

**Method** : `GET`

## Success Responses

**Condition** : The order exists and returns the order details.

**Code** : `200 OK`

**Content Type** : `application/json` 
### Headers


**Currency**: (Optional) Currency of the total that returns the [Order Object](#order-object). Default value is: **MXN**

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
        "id": "09198b42-975f-4ed7-8144-65ff3ded44e0",
        "client": "Miguel Nuno",
        "products": [
            {
                "id":"3702b220-1d29-457d-b0be-f2cc6a6a2ac8",
                "name": "headset",
                "price": 100.00,
                "currency": "USD",
                "quantity": 1
            },
            {
                "id":"98c04d07-5425-48c5-87f2-12d6d921115b",
                "name":"mouse",
                "price": 560.05,
                "currency": "MXN",
                "quantity": 1
            }
        ],
        "total":2328.45,
        "currency":"MXN"
    }  
}
```

## Error Responses


**Condition** : If the order doesn't exists and returns error message.

**Code** : `404 NOT_FOUND`

**Message** : `Order with {id} not found`