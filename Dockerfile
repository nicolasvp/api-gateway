FROM maven:3.5-alpine as builder
COPY . /microservice
RUN cd /microservice && mvn clean package -DskipTests=true

FROM openjdk:8-alpine
MAINTAINER nicolasverapalominos@gmail.com
LABEL version=1.0
LABEL description="Zuul api gateway server"
LABEL vendor="Nicolas"
COPY --from=builder /microservice/target/api-gateway-0.0.1-SNAPSHOT.jar /opt/zuul-server.jar
EXPOSE 3333
CMD java -jar /opt/zuul-server.jar