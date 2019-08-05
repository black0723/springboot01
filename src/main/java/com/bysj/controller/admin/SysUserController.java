package com.bysj.controller.admin;

import com.bysj.beans.User;
import com.bysj.controller.BaseController;
import com.bysj.service.CustomizeService;
import com.bysj.service.UserService;
import com.bysj.utils.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class SysUserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    CustomizeService<User> customizeService;

    @GetMapping("/list")
    public MessageHelper getData() {
        List<User> list = customizeService.getBySql(User.class, "SELECT a.*,b.name AS usertype FROM t_user AS a LEFT JOIN t_role AS b ON a.roleId = b.id");
        return MessageHelper.ok(list);
    }

    @PostMapping("/save")
    public MessageHelper save(@RequestBody User bean) {
        boolean b = userService.saveOrUpdate(bean);
        if (b) {
            return MessageHelper.ok();
        } else {
            return MessageHelper.no();
        }
    }
}
