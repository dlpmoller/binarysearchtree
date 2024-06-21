FROM eclipse-temurin:17
COPY binarysearchtree/target/binarysearchtree-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
