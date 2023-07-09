FROM openjdk:17-jdk-slim
COPY target/alertmanager-adapter.jar alertmanager-adapter.jar
ENTRYPOINT exec java $JAVA_OPTS -jar alertmanager-adapter.jar
