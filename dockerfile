FROM openjdk:11

COPY ./build/libs/*.jar app.jar

EXPOSE 80
ENTRYPOINT ["java", "-jar","app.jar"]