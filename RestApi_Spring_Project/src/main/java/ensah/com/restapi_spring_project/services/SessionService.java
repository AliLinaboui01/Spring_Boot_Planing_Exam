package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.exam.Session;
import ensah.com.restapi_spring_project.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getAllSession() {
        return sessionRepository.findAll();
    }

    public void save(Session session) {
        sessionRepository.save(session);
    }
}
