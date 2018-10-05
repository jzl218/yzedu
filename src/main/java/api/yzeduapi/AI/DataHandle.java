package api.yzeduapi.AI;

import api.yzeduapi.entity.Student;
import api.yzeduapi.entity.Studentcourse;
import api.yzeduapi.repository.CourseRepository;
import api.yzeduapi.repository.StudentCourseRepository;
import api.yzeduapi.repository.StudentRepository;
import api.yzeduapi.sevice.StudentService;
import api.yzeduapi.sevice.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class DataHandle {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private StudentRepository studentRepository;

    public void  dataSava() throws IOException {

        File file = new File("data/recommanddata.txt");
        if (file.exists())
            file.delete();
        file.createNewFile();
        PrintWriter pw = new PrintWriter(file);
        List<Studentcourse> studentcourses = studentCourseRepository.findAll();
        studentcourses.stream()
                .forEach(studentcourse -> {
                    pw.println(studentcourse.getStudent() + "," + studentcourse.getCourse() + "," + 5.0);
                });

    }



    }


