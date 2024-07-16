FROM maven:3.9-sapmachine-17 AS MAVEN_BUILD

COPY pom.xml /build/

COPY src /build/src/

WORKDIR /build/

RUN mvn package -DskipTests=true

FROM ibm-semeru-runtimes:open-17.0.8_7-jdk

EXPOSE 8080

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/products-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "products-0.0.1-SNAPSHOT.jar"]