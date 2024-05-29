package ensah.com.restapi_spring_project.Dto.Request.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupDtoRequest {
    private List<Integer> profIds;

}
