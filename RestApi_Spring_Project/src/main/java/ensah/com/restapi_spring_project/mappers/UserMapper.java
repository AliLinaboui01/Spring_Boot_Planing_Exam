package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.user.UserResponse;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.ProfRepository;
import ensah.com.restapi_spring_project.security.user.Role;
import ensah.com.restapi_spring_project.security.user.User;
import ensah.com.restapi_spring_project.services.ProfService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapper {

    private final ProfRepository profService;

    public static UserResponse mapToUserResponse(User user){
        return  UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();



    }
}
