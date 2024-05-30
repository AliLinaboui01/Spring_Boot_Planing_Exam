package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.exam.ExamTypeResponse;
import ensah.com.restapi_spring_project.models.exam.ExamType;

public class ExamTypeMapper {

    public static ExamTypeResponse mapToExamResponse(ExamType examType){
        return ExamTypeResponse.builder()
                .name(examType.getTitle())
                .build();
    }
}
