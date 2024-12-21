FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY ./pom.xml ./
RUN mvn dependency:go-offline
COPY ./src ./src
RUN mvn package -DskipTests

FROM openjdk:17-oracle
WORKDIR /app
COPY target/mc-Orders-0.0.1-SNAPSHOT.jar mc-Orders.jar
CMD ["java","-jar","mc-Orders.jar"]



