package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Responce.field.FieldResponse;
import ensah.com.restapi_spring_project.Dto.Responce.session.SessionResponse;
import ensah.com.restapi_spring_project.models.element.Field;
import ensah.com.restapi_spring_project.models.exam.Session;
import ensah.com.restapi_spring_project.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<SessionResponse> getAllSession() {
        List<Session> sessions =  sessionRepository.findAll();
        return  sessions.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    private SessionResponse convertToDto(Session session) {
        return SessionResponse.builder()
                .name(session.getTitle())
                .build();
    }

    public void save(Session session) {
        sessionRepository.save(session);
    }

}
