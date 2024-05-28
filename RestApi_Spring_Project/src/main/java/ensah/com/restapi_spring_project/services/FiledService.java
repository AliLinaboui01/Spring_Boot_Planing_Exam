package ensah.com.restapi_spring_project.services;

import ensah.com.restapi_spring_project.Dto.Responce.FiledDto;
import ensah.com.restapi_spring_project.Dto.Responce.field.FieldResponse;
import ensah.com.restapi_spring_project.Dto.request.field.CreateFieldDto;
import ensah.com.restapi_spring_project.models.element.Field;
import ensah.com.restapi_spring_project.repositories.FiledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiledService {

    private final FiledRepository filedRepository;

    @Autowired
    public FiledService(FiledRepository filedRepository) {
        this.filedRepository = filedRepository;
    }





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
        filedRepository.save(field);
        return FieldResponse.
                builder()
                .id(field.getId())
                .name(field.getName())
                .build();
    }
}
