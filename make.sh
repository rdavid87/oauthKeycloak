cd eureka-server/
mvn clean && mvn package -DskipTests
docker build . -t eureka-server
cd ..

cd gateway-api/
mvn clean && mvn package -DskipTests
docker build . -t gateway-api
cd ..

cd movies-api/
mvn clean && mvn package -DskipTests
docker build . -t movie-service
cd ..

cd users-service/
mvn clean && mvn package -DskipTests
docker build . -t user-service
cd ..

cd ms-bills/
mvn clean && mvn package -DskipTests
docker build . -t bill-service
cd ..

docker-compose up