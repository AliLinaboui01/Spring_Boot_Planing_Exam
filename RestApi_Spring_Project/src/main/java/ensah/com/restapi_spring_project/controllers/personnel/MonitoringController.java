package ensah.com.restapi_spring_project.controllers.personnel;


import ensah.com.restapi_spring_project.Dto.Responce.monitoring.MonitoringResponse;
import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import ensah.com.restapi_spring_project.services.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/monitoring")
@PreAuthorize("hasRole('ADMIN')")
public class MonitoringController {

    private final MonitoringService monitoringService;

    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public ResponseEntity<List<MonitoringResponse>> getAllMonitorings() {
        return ResponseEntity.ok(monitoringService.getAllMonitorings());
    }
}
