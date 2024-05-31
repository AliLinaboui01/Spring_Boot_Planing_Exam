package ensah.com.restapi_spring_project.services;


import ensah.com.restapi_spring_project.Dto.Request.exam.CreateExamDto;
import ensah.com.restapi_spring_project.Dto.Responce.exam.ExamResponse;
import ensah.com.restapi_spring_project.Dto.Request.monitoring.MonitoringDto;
import ensah.com.restapi_spring_project.mappers.ExamMapper;
import ensah.com.restapi_spring_project.models.element.PedagogicalElement;
import ensah.com.restapi_spring_project.models.exam.*;
import ensah.com.restapi_spring_project.models.personnel.Admin;
import ensah.com.restapi_spring_project.models.personnel.Monitoring;
import ensah.com.restapi_spring_project.models.personnel.Prof;
import ensah.com.restapi_spring_project.repositories.*;
import org.springframework.beans.factory.annotation.Value;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;
    private final MonitoringRepository monitoringRepository;
    private final SemesterRepository semesterRepository;
    private final ExamTypeRepository examTypeRepository;
    private final SessionRepository sessionRepository;
    private final PedagogicalElementRepository pedagogicalElementRepository;
    private final RoomRepository roomRepository;
    private final ProfRepository profRepository;
    private final AdminRepository adminRepository;
    private final GroupService groupService;
    private final ProfService profService;
    private final AdminService adminService;
    private final MonitoringService monitoringService;
    @Value("${file.upload-dir}")
    private String uploadDir;
    public List<ExamResponse> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        return exams.stream().map(ExamMapper::mapToExamResponse).collect(Collectors.toList());
    }

    @Transactional
    public ExamResponse createExam(CreateExamDto examDto) throws IOException {

        var exam = Exam.builder()
                .start_date(examDto.getStartDate())
                .exactTime(examDto.getExactTime())
                .defaultTime(examDto.getDefaultTime())
                .year(Year.parse(examDto.getYear()))
                .build();

        if (examDto.getSessionId() != null) {
            Session session = sessionRepository.findById(examDto.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session not found"));
            exam.setSession(session);
        }

        var startDate = examDto.getStartDate();
        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localStartDate.getMonthValue();
        if (month >= 9 || month <= 2) {
            var semester = semesterRepository.findByTitle("spring");
            exam.setSemester(semester);
        } else {
            var semester = semesterRepository.findByTitle("autumn");
            exam.setSemester(semester);
        }

        if (examDto.getExamTypeId() != null) {
            ExamType examType = examTypeRepository.findById(examDto.getExamTypeId())
                    .orElseThrow(() -> new RuntimeException("ExamType not found"));
            exam.setExamType(examType);
        }

        if (examDto.getPedagogicalElementId() != null) {
            PedagogicalElement pedagogicalElement = pedagogicalElementRepository.findById(examDto.getPedagogicalElementId())
                    .orElseThrow(() -> new RuntimeException("PedagogicalElement not found"));
            LocalDateTime startDateTime = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
            LocalDateTime endDateTime;
            System.out.println("Type of this element: " + pedagogicalElement.getTitle());
            if (pedagogicalElement.getElementType().getTitle().equals("Module")) {
                endDateTime = startDateTime.plusHours(2);
            } else {
                endDateTime = startDateTime.plusHours(1).plusMinutes(30);
            }
            Date endDate = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());
            exam.setEnd_date(endDate);
            exam.setPedagogicalElement(pedagogicalElement);
            // Check for element clash
            List<Exam> elementClashExams = examRepository.findByPedagogicalElementIdAndDateRange(
                    examDto.getPedagogicalElementId(), examDto.getStartDate(), exam.getEnd_date());
            if (!elementClashExams.isEmpty()) {
                throw new RuntimeException("An exam with the same pedagogical element already exists in the given date range.");
            }
        }
        // Check for date clash
        List<Exam> dateClashExams = examRepository.findOverlappingExams(examDto.getStartDate(), exam.getEnd_date());
        if (!dateClashExams.isEmpty()) {
            throw new RuntimeException("An exam already exists in the given date range.");
        }
        List<Room> occupiedRooms = roomRepository.findOccupiedRooms(examDto.getStartDate(), exam.getEnd_date());
        List<Prof> occupiedProfs = profRepository.findOccupiedProfs(examDto.getStartDate(), exam.getEnd_date());
        List<Admin> occupiedAdmins = adminRepository.findOccupiedAdmins(examDto.getStartDate(), exam.getEnd_date());

        Random random = new Random();

        // Select one random available coordinator prof
        List<Prof> allProfs = profService.getAllProfsforExam();
        List<Prof> availableProfs = new ArrayList<>();
        for (Prof prof : allProfs) {
            if (!occupiedProfs.contains(prof)) {
                availableProfs.add(prof);
            }
        }
        if (availableProfs.isEmpty()) {
            throw new RuntimeException("No available professor to assign as coordinator.");
        }
        Prof randomProfCoordinator = availableProfs.get(random.nextInt(availableProfs.size()));

        // Select one random available admin
        List<Admin> allAdmins = adminService.getAll();
        List<Admin> availableAdmins = new ArrayList<>();
        for (Admin admin : allAdmins) {
            if (!occupiedAdmins.contains(admin)) {
                availableAdmins.add(admin);
            }
        }
        if (availableAdmins.isEmpty()) {
            throw new RuntimeException("No available admin to assign for monitoring.");
        }
        Admin randomAdminMonitoring = availableAdmins.get(random.nextInt(availableAdmins.size()));

        List<Monitoring> buildListMonitoring = new ArrayList<>();

        for (MonitoringDto monitoringDto : examDto.getMonitoringList()) {
            Room room = roomRepository.findById(monitoringDto.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found"));
            if (occupiedRooms.contains(room)) {
                throw new RuntimeException("Room " + room.getName() + " is not available during the selected time.");
            }

            List<Prof> profs = groupService.getAllProfsByGroupIdForExam(monitoringDto.getGroupId());
            List<Prof> listOfProf = new ArrayList<>();

            for (Prof prof : profs) {
                if (!occupiedProfs.contains(prof)) {
                    listOfProf.add(prof);
                    // Set the group reference in Prof
                    prof.setGroup(prof.getGroup());
                    profRepository.save(prof);
                    if (listOfProf.size() == monitoringDto.getProfNumber()) {
                        break;
                    }
                }
            }
            if (listOfProf.size() < monitoringDto.getProfNumber()) {
                throw new RuntimeException("Not enough available professors for the group " + monitoringDto.getGroupId());
            }

            Monitoring monitoring = new Monitoring();
            monitoring.setRoom(room);
            monitoring.setProfs(listOfProf);
            monitoring.setProfCoordinator(randomProfCoordinator);
            monitoring.setAdminMonitoring(randomAdminMonitoring);
            monitoring.setExam(exam);
            monitoringService.save(monitoring);
            buildListMonitoring.add(monitoring);
        }
        exam.setListExamMonitoring(buildListMonitoring);
        examRepository.save(exam);

        return ExamResponse.builder()
                .defaultTime(exam.getDefaultTime())
                .year(examDto.getYear())
                .startDate(exam.getStart_date())
                .endDate(exam.getEnd_date())
                .exactTime(exam.getExactTime())
                .build();
    }


    private String storeFile(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            Path copyLocation = Paths.get(uploadDir + file.getOriginalFilename());
            Files.copy(file.getInputStream(), copyLocation);
            return copyLocation.toString();
        }
        return null;
    }

}






