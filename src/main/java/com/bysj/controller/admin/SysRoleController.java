package com.bysj.controller.admin;

import com.bysj.beans.Role;
import com.bysj.controller.BaseController;
import com.bysj.service.RoleService;
import com.bysj.utils.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class SysRoleController extends BaseController {

    @Autowired
    RoleService roleService;

    @GetMapping("/list")
    public MessageHelper getData() {
        List<Role> list = roleService.list();
        return MessageHelper.ok(list);
    }

    @PostMapping("/save")
    public MessageHelper save(@RequestBody Role bean) {
        boolean b = roleService.saveOrUpdate(bean);
        if (b) {
            return MessageHelper.ok();
        } else {
            return MessageHelper.no();
        }
    }
}
