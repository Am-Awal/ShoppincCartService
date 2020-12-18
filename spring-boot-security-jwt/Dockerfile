# start with a base containing java runtime
FROM openjdk:8-jdk-alpine

#Add maintainer info
LABEL maintainer = "am.awal@outlook.com"

#add a volume pointing to /tmp
VOLUME /tmp

# Make PORT 9031 AVAILABLE TO THE OUTSIDE WORLD
EXPOSE 9005

# application jar
ARG JAR_FILE=target/spring-boot-security-jwt-0.0.1-SNAPSHOT.jar

#add this JAR to the container
ADD ${JAR_FILE} spring-boot-security-jwt.jar

#Run the jar file
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom","-jar","/spring-boot-security-jwt.jar" ]


