package api.yzeduapi.web.controller;

import api.yzeduapi.Dto.UpdatePassDto;
import api.yzeduapi.constant.ErrorCode;
import api.yzeduapi.entity.Student;
import api.yzeduapi.exception.UserErrorException;
import api.yzeduapi.repository.StudentRepository;
import api.yzeduapi.sevice.StudentService;
import api.yzeduapi.sevice.impl.AccountProviderImpl;
import api.yzeduapi.sevice.impl.StudentServiceImpl;
import api.yzeduapi.utils.ResultUtil;
import api.yzeduapi.vo.Result;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private AccountProviderImpl accountProvider;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/emailcode")
    public Result sendEmailCode(HttpSession session) {
        Student student=accountProvider.getNowUser();
        String code =studentService.sendEmailCode(student.getEmail());
        log.debug("email code add to session is " + code);
        session.setAttribute("emailCode", code);
        return ResultUtil.Success();
    }

    @PostMapping("/updatepass")
    public Result updateLoginPass(@RequestBody UpdatePassDto updatePassDto,HttpSession session){
        Student student=accountProvider.getNowUser();
        if (student.getEmail()==null){
            throw new UserErrorException(ErrorCode.EMAIL_NOT_BOUNDED);
        }
        if (updatePassDto.getMailCode()==null||!updatePassDto.getMailCode().equals(session.getAttribute("emailCode")))
            throw new UserErrorException(ErrorCode.EAMIL_CODE_WRONG);
        student.setLoginpass(updatePassDto.getNewPass());
        if (studentRepository.save(student)==null)
            throw new UserErrorException(ErrorCode.UPDATE_MESSAGE_ERROR);
        return ResultUtil.Success();
    }

    @GetMapping("/setemail")
    public Result setEmail(String email){
        Student student=accountProvider.getNowUser();
        if (student.getEmail()!=null){
            throw new UserErrorException(ErrorCode.EAMIL_HAS_BOUNDED);
        }
        student.setEmail(email);
        if (studentRepository.save(student)==null)
            throw new UserErrorException(ErrorCode.EMAIL_SET_ERROR);
        return ResultUtil.Success();
    }


}
