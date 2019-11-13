FROM openjdk:8
MAINTAINER nicolasverapalominos@gmail.com
ADD /target/api-gateway-0.0.1-SNAPSHOT.jar zuul-server.jar
ENTRYPOINT ["java","-jar","zuul-server.jar"]
EXPOSE 5555
VOLUME /var/lib/phrases/zuul-server