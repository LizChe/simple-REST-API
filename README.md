# Simple REST API

A simple REST API that allows to create and get a customer by id.

__Example of GET Request__:

```http request
localhost:8080/api/customer/{id}
```

__Example of POST Request__:

```json
{
    "name": "Hendrix",
    "address": {
        "city": "Cracow",
        "country": "Poland",
        "street": "Jimi H.",
        "zipCode": "11-1942"
    }
}
```