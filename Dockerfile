FROM bellsoft/liberica-openjdk-alpine-musl:11

WORKDIR /app

COPY ./gradle /app/gradle
COPY ./gradlew /app/gradlew

RUN sh gradlew -v

COPY ./src /app/src
COPY ./build.gradle /app/build.gradle
COPY ./settings.gradle /app/settings.gradle

RUN sh gradlew compileJava compileTestJava

# docker build -f Dockerfile -t jvm-dood .
# docker run -e TESTCONTAINERS_HOST_OVERRIDE=host.docker.internal -v /var/run/docker.sock:/var/run/docker.sock jvm-dood sh /app/gradlew test --info
