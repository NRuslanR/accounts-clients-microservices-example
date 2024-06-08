FROM accounts-clients-microservices-example-base
ARG appVersion=0.0.1-SNAPSHOT
ENV ACTIVE_PROFILES=default,dev
WORKDIR /app
COPY ./target/api_gateway-${appVersion}.jar app.jar
ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=${ACTIVE_PROFILES} -jar app.jar" ]
