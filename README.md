# docker-testcontainers-poc

testing testcontainers library running in docker in github actions

### how to run locally 

```
docker build -f Dockerfile -t jvm-dood .
docker run -e TESTCONTAINERS_HOST_OVERRIDE=host.docker.internal -v /var/run/docker.sock:/var/run/docker.sock jvm-dood sh /app/gradlew test --info
```

Note: If you are using Docker Desktop, you need to configure the TESTCONTAINERS_HOST_OVERRIDE environment variable to use the special DNS name host.docker.internal for accessing the host from within a container, which is provided by Docker Desktop.

In other cases it might be omitted.
