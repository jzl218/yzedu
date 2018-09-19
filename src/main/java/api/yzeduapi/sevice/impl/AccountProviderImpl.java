package api.yzeduapi.sevice.impl;

import api.yzeduapi.constant.ErrorCode;
import api.yzeduapi.entity.Student;
import api.yzeduapi.exception.UserNotFoundException;
import api.yzeduapi.sevice.StudentService;
import api.yzeduapi.utils.JsonUtils;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationException;
import org.jsets.shiro.authc.JsetsJwtMatcher;
import org.jsets.shiro.model.Account;
import org.jsets.shiro.service.ShiroAccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Getter
public class AccountProviderImpl implements ShiroAccountProvider {
    @Autowired
    private StudentService studentService;

    private Student nowUser;

    @Override
    public Account loadAccount(String account) throws AuthenticationException {
        return nowUser;
    }

    @Override
    public Set<String> loadRoles(String account) {
        String jwt=JsonUtils.getAccount(account);
        nowUser=studentService.findByStunum(jwt);
        Set<String> roles=new HashSet<>();
        roles.add("role");
        return roles;
    }

    @Override
    public Set<String> loadPermissions(String s) {
        return null;
    }
}
