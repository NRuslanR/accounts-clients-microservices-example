FROM accounts-clients-microservices-example-base
ARG appVersion=0.0.1-SNAPSHOT
ENV ACTIVE_PROFILES=default,dev

RUN mkdir build
COPY . build
RUN cd build && \
    mvn package -DskipTests && \
    cp target/accounts_view_service-${appVersion}.jar ../app.jar && \
    cd .. && \
    rm -rf build

ENTRYPOINT [ "sh", "-c", "java -Dspring.profiles.active=${ACTIVE_PROFILES} -jar app.jar" ]
