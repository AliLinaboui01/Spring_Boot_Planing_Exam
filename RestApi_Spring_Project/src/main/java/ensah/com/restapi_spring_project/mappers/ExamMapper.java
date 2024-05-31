package ensah.com.restapi_spring_project.mappers;

import ensah.com.restapi_spring_project.Dto.Responce.exam.ExamResponse;
import ensah.com.restapi_spring_project.models.exam.Exam;

import java.time.Year;

public class ExamMapper {

    public static ExamResponse mapToExamResponse(Exam exam){
        return ExamResponse.builder()
                .id(exam.getId())
                .startDate(exam.getStart_date())
                .endDate(exam.getEnd_date())
                .year(exam.getYear().toString())
                .defaultTime(exam.getDefaultTime()/60)
                .exactTime(exam.getExactTime()/60)
                .examTitle(exam.getPedagogicalElement().getTitle())
                .session(exam.getSession().getTitle())
                .build();
    }
}
