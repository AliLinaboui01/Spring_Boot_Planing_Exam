package ensah.com.restapi_spring_project.Dto.Responce.department;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartementDto {

    private Integer id;
    private String departement_name;
}
