FROM maven:3.6-openjdk-11
ENV PROJECT_DIR=/Users/olegposokhin/IdeaProjects/OTUS/2020-11-otus-spring-posokhin/hw17
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR
ADD ./pom.xml $PROJECT_DIR
ADD ./src/ $PROJECT_DIR/src
RUN mvn -B clean install -DskipTests -Dcheckstyle.skip


FROM openjdk:11-jre-slim
ENV PROJECT_DIR=/Users/olegposokhin/IdeaProjects/OTUS/2020-11-otus-spring-posokhin/hw17
RUN mkdir -p /opt/
WORKDIR /opt/
COPY --from=0 $PROJECT_DIR/target/hw17-SNAPSHOT-* /opt/
EXPOSE 8080
CMD ["java", "-jar", "/opt/hw17-SNAPSHOT-1.0.jar"]
