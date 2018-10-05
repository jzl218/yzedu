package api.yzeduapi.web.controller;

import api.yzeduapi.Dto.PracticeSubmitDto;
import api.yzeduapi.constant.ErrorCode;
import api.yzeduapi.entity.*;
import api.yzeduapi.exception.UserErrorException;
import api.yzeduapi.repository.*;
import api.yzeduapi.sevice.ChapterService;
import api.yzeduapi.sevice.CourseService;
import api.yzeduapi.sevice.StudentCourseService;
import api.yzeduapi.sevice.impl.AccountProviderImpl;
import api.yzeduapi.utils.BeanUtils;
import api.yzeduapi.utils.PicUtil;
import api.yzeduapi.utils.ResultUtil;
import api.yzeduapi.vo.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private StudentCourseService studentCourseService;
    @Autowired
    private AccountProviderImpl accountProvider;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private StudentChapterRepository studentChapterRepository;
    @Autowired
    private PracticeRepository practiceRepository;
    @Autowired
    private PracticeSubmitRepository practiceSubmitRepository;
    @Autowired
    private WrongQustionRepository wrongQustionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentCourseRepository studentCourseRepository;


    @GetMapping("/getstudentcourse")
    public Result getstudentCourse() {
        Student student=accountProvider.getNowUser();
        List<Studentcourse> studentcourses=studentCourseService.findcourseByStudent(student.getId());
        if (studentcourses!= null) {
            List<StudentCourseVO> studentCourseVOS=studentcourses.stream()
                    .filter(studentcourse -> {return courseService.findById(studentcourse.getCourse()).getIsenabled()==0;})
                    .map(studentcourse -> {
                        Course course=courseService.findById(studentcourse.getCourse());
                        List<Chapter> chapters=chapterService.findByCourse(course.getId());
                StudentCourseVO studentCourseVO=new StudentCourseVO();
                BeanUtils.copyProperties(studentcourse,studentCourseVO);
                studentCourseVO.setProcess((double)chapters.size()/course.getIsenabled());
                studentCourseVO.setCoursename(course.getName());
                return studentCourseVO;
            }).collect(Collectors.toList());
            return ResultUtil.Success(studentCourseVOS);
        }
        else return ResultUtil.Success();

    }


    @GetMapping("/getcourse")
    public Result getCourse(int course) throws IOException {
        if (courseService.findById(course)==null
                ||courseService.findById(course).getIsenabled()!=0)
        throw new UserErrorException(ErrorCode.COURSE_NOT_FOUND);
        CourseVO courseVO=courseService.GetCourseDetail(course);
        return ResultUtil.Success(courseVO);
    }


    @GetMapping("/getchapter")
    public Result getChapter(int course) {
        if (chapterService.findByCourse(course)==null){
            throw new UserErrorException(ErrorCode.CHAPTER_NOT_FOUND);
        }
        List<ChapterVO> chapterVO=chapterService.getChapter(course);
        return ResultUtil.Success(chapterVO);
    }

    @GetMapping("/getvideo")
    public void getVideo(int chapter,HttpServletResponse response)throws IOException{
        if (chapterService.findById(chapter)==null){
            throw new UserErrorException(ErrorCode.CHAPTER_NOT_FOUND);
        }
        String url=chapterService.findById(chapter).getVideourl();
        File file=new File(url);
        FileInputStream fileInputStream= FileUtils.openInputStream(file);
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName()));
        response.setContentType("multipart/form-data");
        IOUtils.copy(fileInputStream,response.getOutputStream());
    }

    @GetMapping("/getware")
    public void getWare(int chapter,HttpServletResponse response)throws IOException{
        if (chapterService.findById(chapter)==null){
            throw new UserErrorException(ErrorCode.CHAPTER_NOT_FOUND);
        }
        String url=chapterService.findById(chapter).getWareurl();
        File file=new File(url);
        FileInputStream fileInputStream= FileUtils.openInputStream(file);
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName()));
        response.setContentType("multipart/form-data");
        IOUtils.copy(fileInputStream,response.getOutputStream());
    }


    @GetMapping("/setlearned")
    public Result setProcess(int chapter){
        Student student=accountProvider.getNowUser();
        Studentchapter studentchapter=studentChapterRepository
                .findStudentchapterByStudentAndChapter(student.getId(),chapter);
        studentchapter.setIscomplete(0);
        if (studentChapterRepository.save(studentchapter)==null)
            throw new UserErrorException(ErrorCode.CHAPTER_FAILED_UPDATE);
        return ResultUtil.Success();
    }

    @GetMapping("/getpractice")
    public Result getPractice(int course){
        if (practiceRepository.findPracticesByCourse(course)==null)
            throw new UserErrorException(ErrorCode.PRACTICE_NOT_FONUND);
        List<Practice> practices=practiceRepository
                .findPracticesByCourse(course);
        List<PracticeVO> practiceVOs=practices.stream()
                .map(practice -> {
                    PracticeVO practiceVO=new PracticeVO();
                    BeanUtils.copyProperties(practice,practiceVO);
                    return practiceVO;
                }).collect(Collectors.toList());
        return ResultUtil.Success(practiceVOs);
    }

    @GetMapping("/getsubmitedpractice")
    private Result getSubmitedPractice(int practice){
        int student =accountProvider.getNowUser().getId();
        Practicesubmit practicesubmit=practiceSubmitRepository
                .findPracticesubmitByStudentAndPractice(student,practice);
        PracticeSubmitVO practiceSubmitVO=new PracticeSubmitVO();
        if (practicesubmit.getContent()==null||practicesubmit.getContent().equals(""))
            throw new UserErrorException(ErrorCode.PRACTICE_NOT_SUBMIT);
        BeanUtils.copyProperties(practicesubmit,practiceSubmitVO);
        return ResultUtil.Success(practiceSubmitVO);
    }

    @PostMapping("/practicesubmit")
    public Result practiceSubmit(@RequestBody PracticeSubmitDto practiceSubmitDto){
        int student =accountProvider.getNowUser().getId();
        if (practiceSubmitRepository.findPracticesubmitByStudentAndPractice(student,practiceSubmitDto.getPractice())!=null)
            throw new UserErrorException(ErrorCode.PRACTICE_HAS_SUBMITTED);
        Practicesubmit practice=new Practicesubmit();
        BeanUtils.copyProperties(practiceSubmitDto,practice);
        practice.setStudent(student);
        practice.setPractice(practiceSubmitDto.getPractice());
        practice.setContent(practiceSubmitDto.getContent());
        if (practiceSubmitRepository.save(practice)==null)
            throw new UserErrorException(ErrorCode.PRACTICE_SUBMIT_ERROR);
        return ResultUtil.Success();
    }

    @GetMapping("/getwrongquestions")
    public Result getWrongQuestions(int practice){
        int student=accountProvider.getNowUser().getId();
        if (wrongQustionRepository
                .findByPracticeAndStudent(practice,student)==null)
            throw new UserErrorException(ErrorCode.WRONGQUESTION_NOT_FOUND);
        List<Wrongquestion> wrongquestions=wrongQustionRepository
                .findByPracticeAndStudent(practice,student);
        List<WrongQuesionVO> wrongQuesionVOS=wrongquestions
                .stream()
                .map(wrongquestion -> {
                    WrongQuesionVO wrongQuesionVO=new WrongQuesionVO();
                    BeanUtils.copyProperties(wrongquestion,wrongQuesionVO);
                    return wrongQuesionVO;
                }).collect(Collectors.toList());
        return ResultUtil.Success(wrongQuesionVOS);
    }


    @GetMapping("/getchosecourse")
    public Result getChoseCourse(){
        List<Course> courses=courseRepository.findAll();
        List<ChoseCourseVO> choseCourseVOS=courses.stream()
                .map(course -> {
                    ChoseCourseVO choseCourseVO=new ChoseCourseVO();
                    BeanUtils.copyProperties(course,choseCourseVO);
                    try {
                        choseCourseVO.setImg(PicUtil.baseurl(course.getImgurl()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    choseCourseVO.setSchoolname(schoolRepository.findSchoolById(course.getId()).getName());
                    choseCourseVO.setTeachername(teacherRepository.findTeacherById(course.getTeacher()).getName());
                    return choseCourseVO;
                }).collect(Collectors.toList());
        return ResultUtil.Success(choseCourseVOS);

    }

    @GetMapping("/getchosecoursebytype")
    public Result getChoseCourseByType(String type){
        List<Course> courses=courseRepository.findCoursesByTypeLike(type);
        List<ChoseCourseVO> choseCourseVOS=courses.stream()
                .map(course -> {
                    ChoseCourseVO choseCourseVO=new ChoseCourseVO();
                    BeanUtils.copyProperties(course,choseCourseVO);
                    try {
                        choseCourseVO.setImg(PicUtil.baseurl(course.getImgurl()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    choseCourseVO.setSchoolname(schoolRepository.findSchoolById(course.getId()).getName());
                    choseCourseVO.setTeachername(teacherRepository.findTeacherById(course.getTeacher()).getName());
                    return choseCourseVO;
                }).collect(Collectors.toList());
        return ResultUtil.Success(choseCourseVOS);

    }

    @GetMapping("/chosecourse")
    public Result choeseCourse(int course){
        Student student=accountProvider.getNowUser();
        if (courseService.findById(course)==null)
            throw new UserErrorException(ErrorCode.COURSE_NOT_FOUND);
        Studentcourse studentcourse=new Studentcourse();
        studentcourse.setCourse(course);
        studentcourse.setStudent(student.getId());
        studentcourse.setIsclosed(1);
        if (studentCourseRepository.findStudentcourseByStudentAndCourse(student.getId(),course)!=null)
            throw new UserErrorException(ErrorCode.COURSE_HAS_CHOSED);
        if (studentCourseRepository.save(studentcourse)==null){
            throw new UserErrorException(ErrorCode.COURSE_CHOSED_FALIED);
        }

        return ResultUtil.Success();
    }
































}