package kan10.webapp;

import kan10.mock.MockAll;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;


@DataJpaTest
@Transactional
@RunWith(SpringRunner.class)
@ContextConfiguration(initializers = {PostgresContainer.Initializer.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractIT {

    @MockBean
    MockAll mockAll;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresContainer.getInstance();

}