package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.monitoring.MonitoringResponse;
import ensah.com.restapi_spring_project.mappers.ProfessorMapper;
import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import ensah.com.restapi_spring_project.repositories.MonitoringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonitoringService {

    private final MonitoringRepository monitoringRepository;

    public List<MonitoringResponse> getAllMonitorings() {
        List<Monitoring> monitorings = monitoringRepository.findAll();
        return monitorings.stream().map(this::mapToMonitoringResponse).collect(Collectors.toList());
    }


    public MonitoringResponse mapToMonitoringResponse(Monitoring monitoring){
        return MonitoringResponse.builder()
                .coordinator(monitoring.getProfCoordinator().getUser().getFirstName() + " " +monitoring.getProfCoordinator().getUser().getLastName() )
                .admin(monitoring.getAdminMonitoring().getUser().getFirstName() + " "+ monitoring.getAdminMonitoring().getUser().getLastName() )
                .profs(monitoring.getProfs()
                        .stream()
                        .map(ProfessorMapper::mapToProfDto)
                        .collect(Collectors.toList()))
                .build();
    }


    public void save(Monitoring monitoring) {
        monitoringRepository.save(monitoring);
    }
}
