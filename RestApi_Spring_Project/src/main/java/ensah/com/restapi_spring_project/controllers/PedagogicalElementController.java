package ensah.com.restapi_spring_project.controllers;


import ensah.com.restapi_spring_project.models.PedagogicalElement;
import ensah.com.restapi_spring_project.services.PedagogicalElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class PedagogicalElementController {

    private final PedagogicalElementService pedagogicalElementService;

    @Autowired
    public PedagogicalElementController(PedagogicalElementService pedagogicalElementService) {
        this.pedagogicalElementService = pedagogicalElementService;
    }

    public List<PedagogicalElement> getAllPedagogicalElement() {
        return  pedagogicalElementService.getAllPedagogicalElements();
    }
}
