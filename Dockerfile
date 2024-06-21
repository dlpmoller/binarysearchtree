FROM maven AS builder
WORKDIR /src/
COPY pom.xml .
RUN mvn -B dependency:go-offline

COPY . .
RUN mvn package

FROM eclipse-temurin:17
WORKDIR /target
COPY --from=builder /src/target/binarysearchtree-1.0-SNAPSHOT.jar app.jar
EXPOSE 4567
ENTRYPOINT ["java","-jar","/src/target/app.jar"]
