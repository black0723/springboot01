package com.bysj.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.beans.Role;
import com.bysj.beans.User;
import com.bysj.service.RoleService;
import com.bysj.service.UserService;
import com.bysj.utils.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

//SpringBoot Controller接收参数的几种常用方式 https://blog.csdn.net/suki_rong/article/details/80445880

@RestController
@RequestMapping("/admin")
public class SysAccountController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @PostMapping("/login")
    public MessageHelper doLogin(@RequestBody Map<String, Object> paramsMap,
                                 HttpSession session) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(User::getUsername, paramsMap.get("username"))
                .eq(User::getPassword, paramsMap.get("password"))
                .eq(User::getRoleId, paramsMap.get("roleId"));
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            return MessageHelper.build(1, "用户名或者密码错误！");
        } else {
            Role role = roleService.getById(user.getRoleId());
            user.setMenus(role.getMenus());
            user.setUsertype(role.getName());
            session.setAttribute("user", user);
            return MessageHelper.build(0, "验证通过，登录成功！", user);
        }
    }

    @RequestMapping("/logout")
    public MessageHelper logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return MessageHelper.ok();
    }
}
