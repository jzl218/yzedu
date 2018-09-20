package api.yzeduapi.web.controller;

import api.yzeduapi.Dto.SubmitTaskDto;
import api.yzeduapi.constant.ErrorCode;
import api.yzeduapi.entity.Student;
import api.yzeduapi.entity.Task;
import api.yzeduapi.entity.TaskSubmit;
import api.yzeduapi.exception.UserErrorException;
import api.yzeduapi.repository.ClazzRepository;
import api.yzeduapi.repository.TaskRepository;
import api.yzeduapi.repository.TaskSubmitRepository;
import api.yzeduapi.repository.TeacherRepository;
import api.yzeduapi.sevice.impl.AccountProviderImpl;
import api.yzeduapi.utils.ResultUtil;
import api.yzeduapi.vo.Result;
import api.yzeduapi.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private AccountProviderImpl accountProvider;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ClazzRepository clazzRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TaskSubmitRepository taskSubmitRepository;

    @GetMapping("/gettask")
    public Result getTask(){
        Student student=accountProvider.getNowUser();
        String clazz=clazzRepository.findClazzById(student.getClazz()).getName();
        List<Task> tasks=taskRepository.findTasksByClass(clazz);
        List<TaskVO> taskVOS=tasks.stream()
                .map(task -> {
                    TaskVO taskVO=new TaskVO();
                    BeanUtils.copyProperties(task,taskVO);
                    taskVO.setTeachername(teacherRepository.findTeacherById(task.getTeacher()).getName());
                    if (taskSubmitRepository.findTaskSubmitByTaskAndStudent(task.getId(),student.getId())==null)
                        taskVO.setState(1);
                    taskVO.setState(0);
                    return taskVO;
                }).collect(Collectors.toList());
        return ResultUtil.Success(taskVOS);
    }

    @GetMapping("/getenclosure")
    public void getEnclosure(int task, HttpServletResponse response)throws IOException {
        String url=taskRepository.findById(task).get().getEnclosureurl();
        File file=new File(url);
        FileInputStream fileInputStream= FileUtils.openInputStream(file);
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(file.getName()));
        response.setContentType("multipart/form-data");
        IOUtils.copy(fileInputStream,response.getOutputStream());
    }


    @PostMapping("/submittask")
    public Result submitTask(@RequestBody SubmitTaskDto submitTaskDto){
        TaskSubmit taskSubmit=new TaskSubmit();
        BeanUtils.copyProperties(submitTaskDto,taskSubmit);
        taskSubmit.setStudent(accountProvider.getNowUser().getId());
        if (taskSubmitRepository.findTaskSubmitByTaskAndStudent(submitTaskDto.getTask(),accountProvider.getNowUser().getId())!=null)
            throw new UserErrorException(ErrorCode.TASK_HAS_SUBMITTED);
        if (taskSubmitRepository.save(taskSubmit)==null){
            throw new UserErrorException(ErrorCode.TASK_SUBMIT_FAILED);
        }
        return ResultUtil.Success();

    }


}
