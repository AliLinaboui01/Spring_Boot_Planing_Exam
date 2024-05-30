package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.group.GroupResponse;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Group;
import ensah.com.restapi_spring_project.models.personnel.Prof;

public class GroupMapper {

    public static GroupResponse mapToGroupResponse(Group group){
        return GroupResponse.builder()
                .id(group.getId())
                .name(group.getGroup_name())
                .build()  ;
    }

}
