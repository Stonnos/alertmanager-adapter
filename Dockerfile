FROM openjdk:11-jdk-slim
COPY target/alertmanager-adapter.war alertmanager-adapter.war
ENTRYPOINT exec java $JAVA_OPTS -jar alertmanager-adapter.war
