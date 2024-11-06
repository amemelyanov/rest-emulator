FROM alpine/git AS build
WORKDIR /app
RUN git clone https://github.com/amemelyanov/rest-emulator /app

FROM maven:3.8.4-openjdk-17 AS build2
COPY --from=build /app ./app
WORKDIR /app
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build2 /app/target/rest-emulator.jar /app
WORKDIR /app
CMD java -jar rest-emulator.jar
