FROM openjdk:17-alpine
WORKDIR usr/src
COPY ./pom.xml ./pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY ./src ./src
RUN ["chmod", "+x", "mvnw"]
RUN ./mvnw dependency:go-offline -B
RUN ./mvnw clean package && cp target/challenge_mendel-0.0.1-SNAPSHOT.jar challenge_mendel-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "challenge_mendel-0.0.1-SNAPSHOT.jar"]