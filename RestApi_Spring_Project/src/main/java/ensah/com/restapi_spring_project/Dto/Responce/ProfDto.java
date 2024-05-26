package ensah.com.restapi_spring_project.Dto.Responce;


import ensah.com.restapi_spring_project.models.Departement;
import ensah.com.restapi_spring_project.models.Field;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;


    private String departement_name;
    private String field_name;
}
