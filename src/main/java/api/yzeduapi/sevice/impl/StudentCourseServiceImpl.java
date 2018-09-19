package api.yzeduapi.sevice.impl;

import api.yzeduapi.entity.Studentcourse;
import api.yzeduapi.repository.StudentCourseRepository;
import api.yzeduapi.sevice.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Override
    public Studentcourse saveOrUpdate(Studentcourse studentcourse) {
        return studentCourseRepository.save(studentcourse);
    }

    @Override
    public Studentcourse findByTwo(int student, int course) {
        return studentCourseRepository.findStudentcourseByStudentAndCourse(student,course);
    }

    @Override
    public List<Studentcourse> findcourseByStudent(int student) {
        return studentCourseRepository.findByStudent(student);
    }
}
