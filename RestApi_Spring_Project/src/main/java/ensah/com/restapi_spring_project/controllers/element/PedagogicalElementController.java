package ensah.com.restapi_spring_project.controllers.element;


import ensah.com.restapi_spring_project.Dto.Responce.PedagogicalElementDto;
import ensah.com.restapi_spring_project.models.element.PedagogicalElement;
import ensah.com.restapi_spring_project.services.PedagogicalElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/element_pedacogics")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<PedagogicalElementDto> getAllPedagogicalElement() {
        return  pedagogicalElementService.getAllPedagogicalElements();
    }


    @PostMapping("/element_pedacogics")
    @PreAuthorize("hasAuthority('admin:read')")
    public void createElementPedagogical(@RequestBody PedagogicalElement pedagogicalElement) {
          pedagogicalElementService.save(pedagogicalElement);
    }



}
