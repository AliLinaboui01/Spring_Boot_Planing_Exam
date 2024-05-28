package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import ensah.com.restapi_spring_project.repositories.MonitoringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoringService {

    private final MonitoringRepository monitoringRepository;

    @Autowired
    public MonitoringService(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }
    public List<Monitoring> getAllMonitorings() {

        return monitoringRepository.findAll();
    }



    public void save(Monitoring monitoring) {
        monitoringRepository.save(monitoring);
    }
}
