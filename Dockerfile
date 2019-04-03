FROM maven:3.6.0-jdk-11

WORKDIR /poster-backend

COPY target/*.jar backend.jar

# expose port for rest interface
EXPOSE 8333

# provide entry-point
CMD java -jar -Dspring.profiles.active=${SPRING_PROFILE:-""} backend.jar --debug
