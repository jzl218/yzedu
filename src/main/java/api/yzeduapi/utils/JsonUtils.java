package api.yzeduapi.utils;

import net.sf.json.JSONObject;
import org.jsets.shiro.model.StatelessLogined;
import org.jsets.shiro.service.ShiroCryptoService;
import org.jsets.shiro.util.Commons;
import org.jsets.shiro.util.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class JsonUtils {
    public static String getAccount(String jwt){
        String newUser=jwt.substring(4,jwt.length());
        JSONObject jsonobject = JSONObject.fromObject(newUser);
        String email=jsonobject.getString("sub");
        return email;
    }

}
