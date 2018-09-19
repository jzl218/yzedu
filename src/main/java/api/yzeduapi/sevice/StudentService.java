package api.yzeduapi.sevice;

import api.yzeduapi.entity.Student;

import javax.servlet.http.HttpServletRequest;

public interface StudentService {
    Student findByStunum(String stunum);
    Student findByStunumAndPass(String stunum,String loginpass);
    String getToken(String stunum);
}
