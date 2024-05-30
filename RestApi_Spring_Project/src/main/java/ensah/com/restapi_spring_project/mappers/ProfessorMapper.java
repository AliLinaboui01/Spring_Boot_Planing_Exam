package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Prof;

public  class ProfessorMapper {
    public static ProfDto mapToProfDto( Prof prof) {

            return ProfDto.builder()
                    .id(prof.getUser().getId())
                    .firstName(prof.getUser().getFirstName())
                    .lastName(prof.getUser().getLastName())
                    .email(prof.getUser().getEmail())
                    .departement_name(prof.getDepartment().getName())  // Fixed field name
                    .field_name(prof.getField().getName())            // Fixed field name
                    .build();
        }
    public static ProfDto convertToDto(Prof prof) {
        return ProfDto.builder()
                .id(prof.getId())
                .firstName(prof.getUser().getFirstName())
                .lastName(prof.getUser().getLastName())
                .email(prof.getUser().getEmail())
                .departement_name(prof.getDepartment().getName())
                .field_name(prof.getField().getName())
                .build();
    }


}
