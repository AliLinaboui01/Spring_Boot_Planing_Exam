package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.DepartementDto;
import ensah.com.restapi_spring_project.Dto.Responce.ProfDto;
import ensah.com.restapi_spring_project.models.Departement;
import ensah.com.restapi_spring_project.models.Prof;
import ensah.com.restapi_spring_project.repositories.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartementService {
    private final DepartementRepository departementRepository;

    @Autowired
    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }




    public List<DepartementDto> getAllDepartement() {
        return departementRepository.findAll().stream()
                .map(this::mapToDepartementDto)
                .collect(Collectors.toList());
    }

        private DepartementDto mapToDepartementDto(Departement departement) {
        return DepartementDto.builder()
                .id(departement.getId())
                .departement_name(departement.getName())
                .build();
    }
}
