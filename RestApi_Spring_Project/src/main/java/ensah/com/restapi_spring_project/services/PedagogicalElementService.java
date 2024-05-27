package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.DepartementDto;
import ensah.com.restapi_spring_project.Dto.Responce.PedagogicalElementDto;
import ensah.com.restapi_spring_project.Dto.Responce.ProfDto;
import ensah.com.restapi_spring_project.models.element.Department;
import ensah.com.restapi_spring_project.models.element.PedagogicalElement;
import ensah.com.restapi_spring_project.repositories.PedagogicalElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedagogicalElementService {

    private final PedagogicalElementRepository pedagogicalElementRepository;
@Autowired
    public PedagogicalElementService(PedagogicalElementRepository pedagogicalElementRepository) {
        this.pedagogicalElementRepository = pedagogicalElementRepository;
    }

    public List<PedagogicalElementDto> getAllPedagogicalElements() {
    return pedagogicalElementRepository.findAll().stream()
            .map(this::mapToPedacogicalElementDto)
            .collect(Collectors.toList());
    }


    private PedagogicalElementDto mapToPedacogicalElementDto(PedagogicalElement pedagogicalElement) {

        var profCordinator = ProfDto.builder()
                .id(pedagogicalElement.getProf_cord().getUser().getId())
                .firstName(pedagogicalElement.getProf_cord().getUser().getFirstName())
                .lastName(pedagogicalElement.getProf_cord().getUser().getLastName())
                .email(pedagogicalElement.getProf_cord().getUser().getEmail())
                .departement_name(pedagogicalElement.getProf_cord().getDepartment().getName())
                .field_name(pedagogicalElement.getProf_cord().getField().getName())
                .build();

        var profEnsg =  ProfDto.builder()
                .id(pedagogicalElement.getProf_of_elem().getUser().getId())
                .firstName(pedagogicalElement.getProf_of_elem().getUser().getFirstName())
                .lastName(pedagogicalElement.getProf_of_elem().getUser().getLastName())
                .email(pedagogicalElement.getProf_of_elem().getUser().getEmail())
                .departement_name(pedagogicalElement.getProf_of_elem().getDepartment().getName())
                .field_name(pedagogicalElement.getProf_of_elem().getField().getName())
                .build();

        return PedagogicalElementDto.builder()
                .id(pedagogicalElement.getId())
                .title(pedagogicalElement.getTitle())
                .prof_coordinateur(profCordinator)
                .prof_ensg(profEnsg)
                .build();
    }

    public void save(PedagogicalElement pedagogicalElement) {
    pedagogicalElementRepository.save(pedagogicalElement);
    }


    public void remove(PedagogicalElement pedagogicalElement) {
    pedagogicalElementRepository.delete(pedagogicalElement);
    }
}
