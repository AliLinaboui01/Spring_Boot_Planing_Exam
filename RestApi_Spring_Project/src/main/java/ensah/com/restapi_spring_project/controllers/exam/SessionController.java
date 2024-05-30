package ensah.com.restapi_spring_project.controllers.exam;


import ensah.com.restapi_spring_project.Dto.Responce.session.SessionResponse;
import ensah.com.restapi_spring_project.models.exam.Session;
import ensah.com.restapi_spring_project.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/session")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class SessionController {

    private  final SessionService sessionService;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<SessionResponse> getAllSessions() {
        return sessionService.getAllSession();
    }
}
