package ensah.com.restapi_spring_project.Dto.Request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedagogicalElementRequestDto {

    private String title;
    private Integer prof_cord_id;
    private Integer prof_of_elem_id;
    private Integer  level_id;
    private Integer elementType_id;


}
