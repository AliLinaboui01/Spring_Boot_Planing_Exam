package ensah.com.restapi_spring_project.Dto.Responce.group;


import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private Integer id;
    private String group_name;
    List<ProfDto> profDtoList;
}
