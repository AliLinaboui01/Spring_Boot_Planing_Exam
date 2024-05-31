package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.user.UserResponse;
import ensah.com.restapi_spring_project.security.user.User;

public class UserMapper {

    public static UserResponse mapToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
