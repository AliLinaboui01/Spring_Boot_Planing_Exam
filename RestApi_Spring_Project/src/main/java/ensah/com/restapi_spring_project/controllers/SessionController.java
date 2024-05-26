package ensah.com.restapi_spring_project.controllers;


import ensah.com.restapi_spring_project.models.Semester;
import ensah.com.restapi_spring_project.models.Session;
import ensah.com.restapi_spring_project.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class SessionController {

    private  final SessionService sessionService;


    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @GetMapping("/sessions")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Session> getAllSessions() {
        return sessionService.getAllSession();
    }
}
