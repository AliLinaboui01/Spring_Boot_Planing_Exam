package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.DepartementDto;
import ensah.com.restapi_spring_project.models.element.Department;
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

        private DepartementDto mapToDepartementDto(Department department) {
        return DepartementDto.builder()
                .id(department.getId())
                .departement_name(department.getName())
                .build();
    }


    public void save(Department department) {
        departementRepository.save(department);
    }
}
