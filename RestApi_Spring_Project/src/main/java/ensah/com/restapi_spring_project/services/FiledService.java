package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.Dto.Request.elementP.PedagogicalElementResponse;
import ensah.com.restapi_spring_project.Dto.Responce.field.FiledDto;
import ensah.com.restapi_spring_project.Dto.Responce.field.FieldResponse;
import ensah.com.restapi_spring_project.Dto.Request.field.CreateFieldDto;
import ensah.com.restapi_spring_project.models.element.Field;
import ensah.com.restapi_spring_project.models.element.PedagogicalElement;
import ensah.com.restapi_spring_project.repositories.DepartementRepository;
import ensah.com.restapi_spring_project.repositories.FiledRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FiledService {

    private final FiledRepository filedRepository;
    private final DepartementRepository departementRepository;

    public List<FiledDto> getAllFiled() {
        return filedRepository.findAll().stream()
                .map(this::mapToFiledDto)
                .collect(Collectors.toList());
    }

    private FiledDto mapToFiledDto(Field field) {
        return FiledDto.builder()
                .id(field.getId())
                .filed_name(field.getName())

                .build();
    }


    public FieldResponse createField(CreateFieldDto fieldDto) {

        var field = Field.builder()
                .name(fieldDto.getName())
                .build();
        var department = departementRepository.findById(fieldDto.getDepartmentID())
                .orElseThrow(() -> new RuntimeException("department not found"));;
                field.setDepartment(department);
        filedRepository.save(field);
        return FieldResponse.
                builder()
                .name(field.getName())

                .build();
    }

    public List<PedagogicalElementResponse> getAllPedagogicalElementsByFieldId(Integer fieldId) {
        Field field = filedRepository.findById(fieldId)
                .orElseThrow(() -> new RuntimeException("Field not found"));

        return field.getPedagogicalElements().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PedagogicalElementResponse convertToDto(PedagogicalElement pedagogicalElement) {
        return PedagogicalElementResponse.builder()
                .id(pedagogicalElement.getId())
                .name(pedagogicalElement.getTitle())
                .IdType(pedagogicalElement.getElementType().getId())
                .nameType(pedagogicalElement.getElementType().getTitle())
                .build();
    }



}
