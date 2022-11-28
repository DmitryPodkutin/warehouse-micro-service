package com.gmail.podkutin.dmitry;

import org.junit.jupiter.api.TestInstance;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("/application-test.properties")
@Sql({"/initDB_ForTest.sql", "/populateDb_ForTest.sql"})
public class AbstractServiceTest {
    public static final Integer NOT_FOUND_ID = 2;
}
