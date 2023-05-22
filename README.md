# Starwars-API

Starwars-API es una API Restful que sirve información sobre personajes de la saga Star Wars. La API a su vez obtiene estos datos
de la API `Swapi`: `http://swapi.trileuco.com/`.  

## End Points

Starwars-API devuelve un listado paginado de personajes a través del end-point:  
```http://{host}:{port}/swapi-proxy/person-info?name={value}&num_page={value}```  

- `name (string)`: Nombre del personaje por el que se quiere filtrar el listado.
- `num_page (int)`: Número de página. Si no se informa, la API devuelve la primera página.

### Schema de la Respuesta
La API devolverá un listado paginado con el siguiente schema:  
```
{
    "count": int,
    "next": int,
    "previous": int,
    "results": [
        {
            "name": string,
            "gender": string,
            "birth_year": string,
            "planet_name": string,
            "fastest_vehicle_driven": string,
            "films": [
                {
                    "name": string,
                    "release_date": LocalDate
                }
            ]
        }
    ]
}
```
- `count`: Número total de personajes.
- `next`: Número de la siguiente página, si existe. `null` en caso contrario.
- `previous`: Número de la página anterior, si existe. `null` en caso contrario.
- `results`: Listado de personajes. **Consta de los siguientes atributos:**
  * `name`: Nombre del personaje.
  * `gender`: Género del personaje.
  * `planet_name`: Nombre del planeta de origen del personaje.
  * `fastest_vehicle_driven`: Nombre del vehículo más rápido pilotado por el personaje.
  * `films`: Listado de películas en las que aparece el personaje. **Consta de los siguientes atributos:**
    * `name`: Nombre de la película.
    * `release_date`: Fecha de lanzamiento de la película.

### Schema de las Excepciones
La API devolverá un respuesta con status 404 en el caso de que no exista el nombre del personaje o el número de página
especificados a través de los parámetros `name` y `num_page`.  

**El body de la respuesta seguirá el siguiente schema:**
```
{
    "timestamp": LocalDateTime,
    "message": string,
    "details": string
}
```
- `timestamp`: Fecha y hora en la cual se produce el error.
- `message`: Mensaje con el error.
- `details`: End-point donde se produce el error.

## Instalación del proyecto

En este repositorio se incluye un archivo **starwars-api-1.0.jar** en el directorio `build/libs/` con la última versión y un **fichero Dockerfile**
en el directorio raíz.

1. **Construir imágen del proyecto.** Sustituir `nombre_imagen` por el nombre que queramos  
```
docker build -t 'nombre_imagen' .
```

2. **Ejecutar imágen en contenedor**. 
* `puerto`: La API escucha peticiones en el puerto 8080, por lo que tendremos que sustiruir `puerto` por el puerto de 
nuestra máquina local donde queramos mapearlo.
* `nombre_contenedor`: **(Opcional)** Nombre que queramos darle al contenedor.
* `nombre_imagen`: Nombre de la imágen creada en el paso anterior.
```
docker run -d -p 'puerto':8080 --name 'nombre_contenedor' 'nombre_imagen'
```

