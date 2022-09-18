# Para ejecutar con Docker

## Crear la imagen

```console
sudo docker compose build --no-cache
```

## Levantar el container

```console
sudo docker compose up
```

## Crear tabla persona

```console
sudo docker compose exec db psql --username=postgres --dbname=sd -c "CREATE TABLE persona (cedula INTEGER PRIMARY KEY, nombre VARCHAR (100), apellido VARCHAR (100));"
```

## Abrir una terminal

```console
docker exec -it lab-socket-java-1 /bin/bash
```

### Dentro de la terminal

- Se puede editar el pom.xml con 'vi' y se coloca el nombre de la clase a compilar
- Para compilar se utiliza el comando

```console
mvn clean package
```

- Para ejecutar el compilado

```console
java -jar nombreArchivo.jar
```

- En la construccion de la imagen ya se compila todas las clases del lab
