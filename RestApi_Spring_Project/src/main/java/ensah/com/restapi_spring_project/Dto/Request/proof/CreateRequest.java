package ensah.com.restapi_spring_project.Dto.Request.proof;

import ensah.com.restapi_spring_project.security.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer fieldId;


}
