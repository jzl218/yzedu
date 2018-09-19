package api.yzeduapi.sevice;

import api.yzeduapi.entity.Course;
import api.yzeduapi.vo.CourseVO;

import java.io.IOException;

public interface CourseService {
    Course findById(int id);
    CourseVO GetCourseDetail(int id) throws IOException;

}
