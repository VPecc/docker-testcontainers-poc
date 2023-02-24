FROM bellsoft/liberica-openjdk-alpine-musl:11

WORKDIR /app

COPY ./gradle /app/gradle
COPY ./gradlew /app/gradlew

RUN sh gradlew -v

COPY ./src /app/src
COPY ./build.gradle /app/build.gradle
COPY ./settings.gradle /app/settings.gradle

RUN sh gradlew compileJava compileTestJava
