# Primer Examen Parcial

## Pruebas
![prueba_parcial_1_distri](https://user-images.githubusercontent.com/61977214/190925538-bd5aff06-264f-465b-a886-53be2f281af6.png)
![operacion_1_distri](https://user-images.githubusercontent.com/61977214/190925546-5541ef76-ec86-4e2c-bef2-b6099fc6ec09.png)
![operacion_2_distri](https://user-images.githubusercontent.com/61977214/190925548-03b840d5-b5a1-4294-a83a-c1d99846d678.png)


## Clases

### En entidad

Se creo la entidad Sensor para referirnos a los datos de los sensores meteorológicos
Se creo el SensorJson en la cual creamos metodos que nos va a permitir mapear de una estructura en forma de JSON a un objeto e viceversa.

### En bd

Se creo el SensorDAO donde aplicamos las operaciones que deseamos, se agrega un CRUD normal, ademas la operacion pedido en el ejercicio que seria traer todos los sensores y obtener la temperatura segun la ciudad especificada
Se creo un Test del SensorDAO donde son datos mocks para utilizar sobre las operaciones creadas.

### En el server udp

Modificamos el cliente y servidor de UDP para que utilice las funcionalidades referente a Sensores.

## Para ejecutar con Docker

## Crear la imagen

```console
docker-compose build --no-cache
```

## Levantar el container

```console
docker-compose up
```

## Crear tabla Sensor

```console
docker-compose exec db psql --username=postgres --dbname=sd -c "CREATE TABLE sensor (id_estacion INTEGER PRIMARY KEY, ciudad VARCHAR (100), porcentaje_humedad NUMERIC, temperatura NUMERIC, velocidad_viento NUMERIC, fecha DATE, hora TIME);"
```

## Abrir una nueva terminal

```console
docker exec -it parcial-distri_java_1 /bin/bash
```

### Abrir una nueva terminal para el Server

```console
mvn clean package
```

Para levantar el servidor

```console
java -jar server.udp.UDPServer\<.jar
```

## Abrir una nueva terminal para el cliente

```console
docker exec -it parcial-distri_java_1 /bin/bash
```

Para levantar el cliente

```console
java -jar server.udp.UDPClient\<.jar
```
