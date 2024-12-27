# franquicia
Franquicia aplicacion

Repositorio https://github.com/juanalejandrog86/franquicia

A continuación se lista el listado de endpoints para cada punto y su forma de uso se ilustra con un ejemplo. La conexion a base de datos se puede modificar en franquicia\src\main\resources\application.properties


2 Adicionar franquicia
POST http://localhost:8080/franquicias
	request 
		{
			"name": "F30",
			"sucursales": []
		}
	response
		{
			"id": 1355,
			"name": "F30"
		}
	
3 Adicionar sucuarsal a franquicia
POST http://localhost:8080/sucursales
	request
	{
		"name":"S20",
		"franquicia":{"name":"F30"}
	}
	response 
	{
		"id": 1353,
		"name": "S20",
		"franquicia": {
			"id": 1355,
			"name": "F30"
		}
	}
4 Adicionar productos a una sucursal
POST http://localhost:8080/productos
	request
	{
		"name":"P31",
		"amount":100,
		"sucursal":{"name":"S20"}
	}
	response
	{
		"id": 1353,
		"name": "P31",
		"amount": 100,
		"sucursal": {
			"id": 1353,
			"name": "S20",
			"franquicia": {
				"id": 1355,
				"name": "F30"
			}
		}
	}

5 Eliminar un producto a una sucursal
DELETE http://localhost:8080/productos
Request
	{
		"name":"P31",
		"sucursal":{"name":"S20"}
	}
Response

6 Modificar el stock de un producto
PUT http://localhost:8080/productos
request
	{
		"name":"P3",
		"amount":120,
		"sucursal":{"name":"S11"}
	}

7 Producto con mas stock por sucursal para una franquicia
GET http://localhost:8080/productos/maxStock/{franquiciaName}
Response
[
    {
        "id": 1356,
        "name": "P33",
        "amount": 103,
        "sucursal": {
            "id": 1353,
            "name": "S20",
            "franquicia": {
                "id": 1355,
                "name": "F30"
            }
        }
    },
    {
        "id": 1358,
        "name": "P35",
        "amount": 10,
        "sucursal": {
            "id": 1354,
            "name": "S21",
            "franquicia": {
                "id": 1355,
                "name": "F30"
            }
        }
    }
]
