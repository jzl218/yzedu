package api.yzeduapi.repository;

import api.yzeduapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    Course findCourseById(int id);
    List<Course> findCoursesByTypeLike(String type);
}
