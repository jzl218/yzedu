package api.yzeduapi.utils;

import api.yzeduapi.entity.Student;
import api.yzeduapi.entity.Studentcourse;
import api.yzeduapi.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommandTest {
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @GetMapping("/datatest")
    public void RecommandTest(){
        for (int i=0;i<=1000;i++){
            int x=(int)(1+Math.random()*20);
            int y=(int)(1+Math.random()*1000);
            Studentcourse studentcourse=new Studentcourse();
            studentcourse.setIsclosed(1);
            studentcourse.setProcess(1);
            studentcourse.setCourse(x);
            studentcourse.setStudent(y);
            studentcourse.setScore(0);
            studentCourseRepository.save(studentcourse);
        }
    }

}
