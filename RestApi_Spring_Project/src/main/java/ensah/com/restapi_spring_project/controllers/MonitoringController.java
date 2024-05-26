package ensah.com.restapi_spring_project.controllers;


import ensah.com.restapi_spring_project.models.Monitoring;
import ensah.com.restapi_spring_project.models.Room;
import ensah.com.restapi_spring_project.services.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class MonitoringController {

    private final MonitoringService monitoringService;

    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }


    @GetMapping("/monitorings")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Monitoring> getAllMonitorings() {

        return monitoringService.getAllMonitorings();
    }
}
