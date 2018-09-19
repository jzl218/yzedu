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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentDao;
    @Autowired
    private AccountProviderImpl accountProvider;
    @Autowired
    private JavaMailSenderImpl mailSender;

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
    @Override
    public String sendEmailCode(String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        msg.setSubject("Verify Code");
        String code = new String(this.genRandCode(6, true));
        msg.setFrom(((JavaMailSenderImpl)mailSender).getUsername());
        msg.setText("Thanks for your using!\n\nYour verify code is: " + code + ", please verify soon!\n\nBest wishes to you!");
        mailSender.send(msg);
        return code;
    }
    @Override
    public char[] genRandCode(int length, boolean numOnly) {
        // 48-57 - 0-9  65-90 A-Z  97-122 a-z
        Random r = new Random();
        r.setSeed(System.currentTimeMillis() + Thread.currentThread().getId());
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            int rand = numOnly ? r.nextInt(10) : r.nextInt(62);
            if (rand < 10) {
                result[i] = (char)(rand + 48);
            } else if (rand > 35) {
                result[i] = (char)(rand + 61);
            } else {
                result[i] = (char)(rand + 55);
            }
        }
        return result;
    }
}
