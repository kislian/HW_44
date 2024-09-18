package ru.hogwarts.school.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final FacultyService facultyService;

    public StudentServiceImpl(StudentRepository studentRepository, FacultyService facultyService) {
        this.studentRepository = studentRepository;
        this.facultyService = facultyService;
    }

    @Override
    public Student getStudent(Long id) {
        logger.info("Find student by id {}", id);

        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student is not found for id" + id));
    }

    @Override
    public Student createStudent(long facultyId, Student student) {
        logger.info("Create student {} with facultyId {}", student, facultyId);

        Faculty faculty = facultyService.getFaculty(facultyId);
        student.setFaculty(faculty);
        return studentRepository.save(student);
    }

    @Override
    public Student editStudent(Long id, Student student) {
        logger.info("Edit student {} with id {}", student, id);

        Student studentFromBd = getStudent(id);
        studentFromBd.setName(student.getName());
        studentFromBd.setAge(student.getAge());
        return studentRepository.save(studentFromBd);
    }

    @Override
    public void deleteStudent(Long id) {
        logger.info("Delete student with id {}", id);

        studentRepository.delete(getStudent(id));
    }

    @Override
    public List<Student> filterByAge(int age) {
        logger.info("Filter by age {}", age);

        return studentRepository.findAllByAge(age);
    }

    @Override
    public List<Student> findAllByAgeBetween(int fromAge, int toAge) {
        logger.info("Find all by age between {} {}", fromAge, toAge);

        return studentRepository.findAllByAgeBetween(fromAge, toAge);
    }

    @Override
    public Faculty getFaculty(Long studentId) {
        logger.info("Get faculty for studentId {}", studentId);

        return getStudent(studentId).getFaculty();
    }

    @Override
    public Integer getAllStudentsCount() {
        logger.info("Get all students Count");

        return studentRepository.getAllCount();
    }

    @Override
    public Integer getAvgAge() {
        logger.info("Get avg Age");

        return studentRepository.getAvgAge();
    }

    @Override
    public List<Student> getLastNStudents(int limit) {
        return studentRepository.findAll(
                PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "id"))).getContent();
    }
}
