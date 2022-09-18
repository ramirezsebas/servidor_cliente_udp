docker-compose up --build -d
docker-compose exec db psql --username=postgres --dbname=sd -c "CREATE TABLE sensor (id_estacion INTEGER PRIMARY KEY, ciudad VARCHAR (100), porcentaje_humedad NUMERIC, temperatura NUMERIC, velocidad_viento NUMERIC, fecha VARCHAR (100), hora VARCHAR (100));"
