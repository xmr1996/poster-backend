FROM maven:3.6.0-jdk-11

WORKDIR /poster-backend

ADD . /poster-backend

RUN mvn -B -T 4 package -DskipTests

# expose port for rest interface
EXPOSE 8333

# provide entry-point
CMD java -jar -Dspring.profiles.active=${SPRING_PROFILE} ./target/poster-backend-*.jar