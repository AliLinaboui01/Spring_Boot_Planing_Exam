package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.PedagogicalElement;
import ensah.com.restapi_spring_project.repositories.PedagogicalElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedagogicalElementService {

    private final PedagogicalElementRepository pedagogicalElementRepository;
@Autowired
    public PedagogicalElementService(PedagogicalElementRepository pedagogicalElementRepository) {
        this.pedagogicalElementRepository = pedagogicalElementRepository;
    }

    public List<PedagogicalElement> getAllPedagogicalElements() {
    return pedagogicalElementRepository.findAll();
    }
}
