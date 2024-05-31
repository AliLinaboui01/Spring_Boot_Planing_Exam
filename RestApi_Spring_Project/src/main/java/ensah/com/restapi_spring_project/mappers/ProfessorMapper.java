package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.prof.ProfDto;
import ensah.com.restapi_spring_project.models.personnel.Prof;

public  class ProfessorMapper {
    public static ProfDto mapToProfDto( Prof prof) {

        ProfDto profDto = ProfDto.builder()
                .id(prof.getUser().getId())
                .firstName(prof.getUser().getFirstName())
                .lastName(prof.getUser().getLastName())
                .email(prof.getUser().getEmail())
                // Fixed field name
                .build();
        if(prof.getDepartment()!= null){
            profDto.setDepartement_name(prof.getDepartment().getName()) ; // Fixed field name

        }
        if (prof.getField()!= null){
            profDto.setField_name(prof.getField().getName());
        }
            return profDto;
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
