package EksamenProgrammeringBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"EksamenProgrammeringBackend.security.entity", "EksamenProgrammeringBackend.models"})
public class JavaSecurityApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaSecurityApiApplication.class, args);
    }
}
