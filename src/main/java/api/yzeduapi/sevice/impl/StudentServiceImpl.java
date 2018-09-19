package api.yzeduapi.sevice.impl;

import api.yzeduapi.entity.Student;
import api.yzeduapi.repository.StudentRepository;
import api.yzeduapi.sevice.StudentService;
import api.yzeduapi.utils.CommonUtil;
import api.yzeduapi.utils.JsonUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jsets.shiro.util.CryptoUtil;
import org.jsets.shiro.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentDao;
    @Autowired
    private AccountProviderImpl accountProvider;

    @Override
    public Student findByStunum(String stunum) {
        return studentDao.findStudentByStunum(stunum);
    }

    @Override
    public Student findByStunumAndPass(String stunum, String loginpass) {
        return studentDao.findStudentByStunumAndLoginpass(stunum,loginpass);
    }

    @Override
    public String getToken(String stunum) {
        Student student=studentDao.findStudentByStunum(stunum);
        String token= CryptoUtil.issueJwt(
                ShiroUtils.getShiroProperties().getJwtSecretKey()
                , stunum
                , stunum
                , 10000*6*60*24*15l
                , "base"
                ,null
                , SignatureAlgorithm.HS512
        );
        return token;
    }

}
