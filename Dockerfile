FROM eclipse-tumurin:21-jre-alpine as builder
WORKDIR extracted
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM eclipse-temurin:21-jre-alpine
COPY --from=builder extracted/dependencies/ ./
COPY --from=builder extracted/snapshot-dependencies/ ./
COPY --from=builder extracted/spring-boot-loader/ ./
COPY --from=builder extracted/application/ ./

EXPOSE 8081
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncer"]