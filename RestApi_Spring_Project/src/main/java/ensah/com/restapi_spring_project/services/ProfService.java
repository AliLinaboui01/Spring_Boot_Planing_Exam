package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.ProfDto;
import ensah.com.restapi_spring_project.models.Prof;
import ensah.com.restapi_spring_project.repositories.ProfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfService {
    private final ProfRepository profRepository;

    @Autowired
    public ProfService(ProfRepository profRepository) {
        this.profRepository = profRepository;
    }



    public List<ProfDto> getAllProfs() {
        return profRepository.findAll().stream()
                .map(this::mapToProfDto)
                .collect(Collectors.toList());
    }

    private ProfDto mapToProfDto(Prof prof) {
        return ProfDto.builder()
                .id(prof.getId())
                .firstName(prof.getFirstName())
                .lastName(prof.getLastName())
                .email(prof.getEmail())
                .departement_name(prof.getDepartement().getName())
                .field_name(prof.getField().getName())
                .build();
    }
}
