package api.yzeduapi.repository;

import api.yzeduapi.entity.Student;
import api.yzeduapi.entity.Studentcourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentCourseRepository extends JpaRepository<Studentcourse,Integer> {
    List<Studentcourse> findByStudent (int student);
    Studentcourse findStudentcourseByStudentAndCourse(int student,int course);
    @Query(value = "select process from studentcourse where student=?1 and course=?2",nativeQuery = true)
    int getProcess(int student,int course);


}
