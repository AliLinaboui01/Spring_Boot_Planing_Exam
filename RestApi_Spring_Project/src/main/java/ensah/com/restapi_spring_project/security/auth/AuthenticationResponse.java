package ensah.com.restapi_spring_project.security.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import ensah.com.restapi_spring_project.security.user.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;

//    private User userDetails;
}
