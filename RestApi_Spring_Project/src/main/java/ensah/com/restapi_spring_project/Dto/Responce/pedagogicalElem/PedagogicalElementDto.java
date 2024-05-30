package ensah.com.restapi_spring_project.Dto.Responce.pedagogicalElem;


import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedagogicalElementDto {

    private Integer id;
    private String title;

    private ProfDto prof_coordinateur;

    private ProfDto prof_ensg;



 }
