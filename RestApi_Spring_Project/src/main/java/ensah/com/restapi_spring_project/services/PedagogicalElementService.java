package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Request.elementP.PedagogicalElementRequestDto;
import ensah.com.restapi_spring_project.Dto.Responce.pedagogicalElem.PedagogicalElementDto;
import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.element.*;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedagogicalElementService {

    private final PedagogicalElementRepository pedagogicalElementRepository;
    private final ProfRepository profRepository;
    private final LevelRepository levelRepository;
    private final ElementTypeRepository elementTypeRepository;
    private final FiledRepository fieldRepository;

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

    @Transactional
    public ResponseEntity<String> create(PedagogicalElementRequestDto pedagogicalElementDto) {
        try {
            // Fetch related entities
            Prof profOfElem = profRepository.findById(pedagogicalElementDto.getProf_of_elem_id())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid prof_of_elem_id"));
            Level level = levelRepository.findById(pedagogicalElementDto.getLevel_id())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid level_id"));
            ElementType elementType = elementTypeRepository.findById(pedagogicalElementDto.getElementType_id())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid elementType_id"));
            var field  = fieldRepository.findById(pedagogicalElementDto.getFieldId())
                    .orElseThrow(()->new IllegalArgumentException("field not found"));

            // Build PedagogicalElement
            PedagogicalElement pedagogicalElement = PedagogicalElement.builder()
                    .title(pedagogicalElementDto.getTitle())
                    .prof_of_elem(profOfElem)
                    .level(level)
                    .elementType(elementType)
                    .field(field)
                    .build();
            //check if the admin selct the cord of this elemnts
            if(pedagogicalElementDto.getProf_cord_id() != null){
                Prof profCord = profRepository.findById(pedagogicalElementDto.getProf_cord_id()).orElseThrow(() -> new IllegalArgumentException("Invalid prof_of_elem_id"));;
                pedagogicalElement.setProf_cord(profCord);
            }
            profOfElem.setField(field);
            profOfElem.setDepartment(field.getDepartment());
            profRepository.save(profOfElem);
            // or the system will affect the prof of elemnt as a default s
            pedagogicalElement.setProf_cord(profOfElem);
            // Save the PedagogicalElement
            pedagogicalElementRepository.save(pedagogicalElement);
            return ResponseEntity.ok("Pedagogical element created successfully");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create this pedagogical element");
        }
    }


    public void remove(PedagogicalElement pedagogicalElement) {
    pedagogicalElementRepository.delete(pedagogicalElement);
    }


}
