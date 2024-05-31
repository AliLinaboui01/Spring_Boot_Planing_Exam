package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Prof;

public class ProfessorMapper {
    public static ProfDto mapToProfDto(Prof prof) {
        ProfDto.ProfDtoBuilder builder = ProfDto.builder()
                .id(prof.getUser().getId())
                .firstName(prof.getUser().getFirstName())
                .lastName(prof.getUser().getLastName())
                .email(prof.getUser().getEmail());

        if (prof.getDepartment() != null) {
            builder.departement_name(prof.getDepartment().getName());
        }

        if (prof.getField() != null) {
            builder.field_name(prof.getField().getName());
        }

        if (prof.getGroup() != null) {
            builder.groupName(prof.getGroup().getGroup_name());
        }

        return builder.build();
    }

    public static ProfDto convertToDto(Prof prof) {
        ProfDto.ProfDtoBuilder builder = ProfDto.builder()
                .id(prof.getId())
                .firstName(prof.getUser().getFirstName())
                .lastName(prof.getUser().getLastName())
                .email(prof.getUser().getEmail());

        if (prof.getDepartment() != null) {
            builder.departement_name(prof.getDepartment().getName());
        }

        if (prof.getField() != null) {
            builder.field_name(prof.getField().getName());
        }

        return builder.build();
    }
}
