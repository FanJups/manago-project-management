package manago.com.restbackend;

import manago.com.restbackend.model.Customer;
import manago.com.restbackend.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.HashSet;

@SpringBootApplication
@EnableJpaAuditing
public class RestBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestBackendApplication.class, args);
    }
}
