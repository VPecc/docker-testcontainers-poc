package com.pecc.poc;

import org.jdbi.v3.testing.junit5.JdbiExtension;
import org.jdbi.v3.testing.junit5.tc.JdbiTestcontainersExtension;
import org.jdbi.v3.testing.junit5.tc.TestcontainersDatabaseInformation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@Testcontainers
public class TestContainersTest {

    @Container
    public PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("test")
            .withUsername("test");


    private static final TestcontainersDatabaseInformation POSTGRES = TestcontainersDatabaseInformation.of(
            null,
            "test",
            null,
            (catalogName, schemaName) -> format("CREATE SCHEMA %s", schemaName)
    );

    @RegisterExtension
    JdbiExtension extension = JdbiTestcontainersExtension.instance(POSTGRES, container);

    @Test
    void assert_db_works() {
        List<String> result = extension
                .getJdbi()
                .withHandle(handle ->
                        handle.createQuery("SELECT datname FROM pg_database order by 1")
                                .mapTo(String.class)
                                .list());

        assertIterableEquals(List.of("postgres", "template0", "template1", "test"), result);
    }





}
