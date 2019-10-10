package kan10.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "kan10")
@Configuration
@EntityScan("kan10")
@EnableJpaRepositories(repositoryFactoryBeanClass = QDataTablesRepositoryFactoryBean.class,basePackages = "kan10")
@SpringBootApplication

public class WebappApplication extends SpringBootServletInitializer /*implements CommandLineRunner*/ {


    public static void main(String[] args) {
        SpringApplication.run(WebappApplication.class, args);
    }
    /*
    * Find main class
    * POM: properties: <start-class>
    * */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebappApplication.class);
    }

    /*public void run (String... args) {
    }*/
}
