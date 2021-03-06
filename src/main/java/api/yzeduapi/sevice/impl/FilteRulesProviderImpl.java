package api.yzeduapi.sevice.impl;

import api.yzeduapi.constant.AuthApis;
import com.google.common.collect.Lists;

import org.jsets.shiro.model.CustomRule;
import org.jsets.shiro.model.RolePermRule;
import org.jsets.shiro.service.ShiroFilteRulesProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class FilteRulesProviderImpl implements ShiroFilteRulesProvider {


    @Override
    public List<RolePermRule> loadRolePermRules() {
        return null;
    }

    @Override
    public List<RolePermRule> loadHmacRules() {
        return null;
    }

    @Override
    public List<RolePermRule> loadJwtRules() {
        List<RolePermRule> jwtRules = Lists.newLinkedList();
        AuthApis[] authApis=AuthApis.values();
        Arrays.stream(authApis).forEach(authApi->{
            RolePermRule jwtRule=new RolePermRule();
            jwtRule.setUrl(authApi.getUrl());
            jwtRule.setNeedRoles("base");
            jwtRules.add(jwtRule);
        });


        return jwtRules;

    }

    @Override
    public List<CustomRule> loadCustomRules() {
        return null;
    }
}
