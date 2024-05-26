package ensah.com.restapi_spring_project;

import ensah.com.restapi_spring_project.security.auth.AuthenticationService;
import ensah.com.restapi_spring_project.security.auth.RegisterDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static ensah.com.restapi_spring_project.security.user.Role.ADMIN;


@SpringBootApplication
public class RestApiSpringProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiSpringProjectApplication.class, args);
	}



}
