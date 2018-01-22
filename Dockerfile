FROM openjdk:7
COPY ./target/ons-totalmobile-integration-0.0.1-SNAPSHOT.jar /opt/totalmobile-ons-integration/
COPY ./target/ons-totalmobile-integration-0.0.1-SNAPSHOT-exec.jar /opt/totalmobile-ons-integration/
WORKDIR /opt/totalmobile-ons-integration
CMD ["java", "-jar", "ons-totalmobile-integration-0.0.1-SNAPSHOT-exec.jar"]
