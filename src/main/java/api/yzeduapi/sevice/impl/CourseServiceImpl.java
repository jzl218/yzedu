package api.yzeduapi.sevice.impl;

import api.yzeduapi.entity.Course;
import api.yzeduapi.entity.Coursepoint;
import api.yzeduapi.entity.Point;
import api.yzeduapi.entity.Teacher;
import api.yzeduapi.repository.*;
import api.yzeduapi.sevice.CourseService;
import api.yzeduapi.vo.CourseVO;
import api.yzeduapi.vo.TeacherVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static api.yzeduapi.utils.PicUtil.*;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private PointRepository pointRepository;
    @Autowired
    private CoursePointRepository coursePointRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AccountProviderImpl accountProvider;
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private ChapterServiceImpl chapterService;
    @Autowired
    private StudentChapterRepository studentChapterRepository;

    @Override
    public CourseVO GetCourseDetail(int id) throws IOException {
        int student=accountProvider.getNowUser().getId();
        Course course=findById(id);
        CourseVO courseVO=new CourseVO();
        BeanUtils.copyProperties(course,courseVO);
        int process=studentChapterRepository
                .findStudentchaptersByStudentAndIscomplete(student,0).size();
        int allcounts=chapterService.findByCourse(course.getId()).size();
        Teacher teacher=teacherRepository.findTeacherById(course.getTeacher());
        TeacherVO teacherVO=new TeacherVO();
        BeanUtils.copyProperties(teacher,teacherVO);
        courseVO.setLearningrate((double)process/allcounts);
        courseVO.setTeacherVO(teacherVO);
        courseVO.setImg(baseurl(course.getImgurl()));
        List<Point> points=new ArrayList<>();
        List<Coursepoint> coursePoints=coursePointRepository.findByCourse(id);
        coursePoints.stream()
                .forEach(coursePoint -> {
                    Point point=pointRepository.findPointById(coursePoint.getPoint());
                    points.add(point);
                });
        courseVO.setPoints(points);
        return courseVO;

    }

    @Override
    public Course findById(int id) {
        return courseRepository.findCourseById(id);
    }


}
