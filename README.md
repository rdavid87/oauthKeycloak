
# Entrega parcial - Trabajo integrador

## Consigna

El trabajo parcial consistirá en entregar la resolución de las prácticas de clase que afectan al trabajo integrador del sistema de películas online. Recordemos nuestra arquitectura y los microservicios que intervienen en la solución:

![image](https://user-images.githubusercontent.com/37912494/202950528-bfbefa0f-d439-4a50-8f80-03776e1de7ee.png)


## 

## Levantar con Docker

Ejecutar el archivo el archivo make.sh el cual entrará a cada uno de los servicios a crear el archivo `.jar` y la imagen del servicio.

Al final levantará todas las imagenes con base al archivo `docker-compose`.


## Levantar con Intellij

Levantar una imagen de keycloak e importar el archivo del realm contenido en el folder `config\keycloak` de este repositorio.

Levantar una imagen de `mongoDB`.

Abrir el repositorio y ejecutar cada uno de los microservicio en el siguiente orden:

- eureka-server
- gateway-api
- movie-service
- bill-service
- user-service

## Importar a Postman

En el directorio `config/DigitalMedia.postman_collection.json` se encuentra todos los endpoints a probar.

