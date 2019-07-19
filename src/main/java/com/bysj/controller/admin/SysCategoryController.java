package com.bysj.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bysj.beans.Category;
import com.bysj.service.CategoryService;
import com.bysj.utils.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class SysCategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getData")
    public MessageHelper getData(@RequestParam Integer parentId) {
        List<Category> list = categoryService.list(new QueryWrapper<Category>().eq("parentId", parentId));
        return MessageHelper.ok(list);
    }

    @PostMapping("/update")
    public MessageHelper update(@RequestBody Category category) {
        boolean b = categoryService.updateById(category);
        if (b) {
            return MessageHelper.ok();
        } else {
            return MessageHelper.no();
        }
    }

    @PostMapping("/add")
    public MessageHelper add(@RequestBody Category category) {
        boolean b = categoryService.save(category);
        if (b) {
            return MessageHelper.ok();
        } else {
            return MessageHelper.no();
        }
    }
}
