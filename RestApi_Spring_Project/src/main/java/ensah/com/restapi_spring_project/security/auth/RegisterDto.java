package ensah.com.restapi_spring_project.security.auth;

import ensah.com.restapi_spring_project.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RegisterDto {

    private String firstName;
    private String lastName;

    private String email;
    private String password;
    private Role role;

}
