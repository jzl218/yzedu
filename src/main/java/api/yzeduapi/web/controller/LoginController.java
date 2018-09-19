package api.yzeduapi.web.controller;

import api.yzeduapi.Dto.StudentDto;
import api.yzeduapi.constant.ErrorCode;
import api.yzeduapi.entity.Student;
import api.yzeduapi.exception.UserErrorException;
import api.yzeduapi.exception.UserNotFoundException;
import api.yzeduapi.sevice.StudentService;
import api.yzeduapi.utils.BeanUtils;
import api.yzeduapi.utils.ResultUtil;
import api.yzeduapi.vo.Result;
import api.yzeduapi.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class LoginController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public Result login(@RequestBody StudentDto studentDto, BindingResult result){
        if (result.hasErrors()){
            throw new UserErrorException(result.getFieldError().getDefaultMessage());
        }
        if (studentService.findByStunum(studentDto.getStunum()).getIsenabled()==0){
            throw new UserNotFoundException(ErrorCode.ACCOUNT_DENIED);
        }
        if (studentService.findByStunumAndPass(studentDto.getStunum(),studentDto.getLoginpass())==null){
            throw new UserNotFoundException(ErrorCode.ACCOUNT_OR_PASSWORD_ERROR);
        }
        String stunum=studentDto.getStunum();
        String token=studentService.getToken(stunum);
        Student student=studentService.findByStunum(stunum);
        StudentVO studentVO=new StudentVO();
        BeanUtils.copyProperties(student,studentVO);
        studentVO.setJwt(token);
        return ResultUtil.Success(studentVO);
    }






}
