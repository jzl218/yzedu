package api.yzeduapi.sevice;

import api.yzeduapi.entity.Studentcourse;

import java.util.List;

public interface StudentCourseService {
    List<Studentcourse> findcourseByStudent(int student);
    Studentcourse findByTwo(int student,int course);
    Studentcourse saveOrUpdate(Studentcourse studentcourse);
}
