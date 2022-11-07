FROM openjdk:11
VOLUME /tmp
EXPOSE 9044
ADD ./target/ms-mobilewalletaccount-0.0.1-SNAPSHOT.jar ms-mobilewalletaccount.jar
ENTRYPOINT ["java","-jar","/ms-mobilewalletaccount.jar"]