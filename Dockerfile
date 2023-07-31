ARG IMAGE_JDK=adoptopenjdk/openjdk11:alpine
ARG IMAGE_JRE=adoptopenjdk/openjdk11:alpine-jre
FROM $IMAGE_JDK as builder
LABEL stage=builder
COPY . .
RUN ./gradlew build --stacktrace --info

FROM $IMAGE_JRE
ARG SERVICE_NAME
ENV ENV_JAVA_PARAM="-Xmx512m"
MAINTAINER SAA
COPY --from=builder ./build/libs/*.jar ./app.jar
ENTRYPOINT java $ENV_JAVA_PARAM -jar app.jar
EXPOSE 8080