package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.models.element.ElementType;
import ensah.com.restapi_spring_project.repositories.ElementTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementTypeService {

    private final ElementTypeRepository elementTypeRepository;


    @Autowired
    public ElementTypeService(ElementTypeRepository elementTypeRepository) {
        this.elementTypeRepository = elementTypeRepository;
    }


    public List<ElementType> getAllElementType() {
        return elementTypeRepository.findAll();
    }

    public void save(ElementType elementType) {
        elementTypeRepository.save(elementType);
    }
}
