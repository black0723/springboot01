package com.bysj.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bysj.beans.Category;
import com.bysj.beans.Product;
import com.bysj.controller.BaseController;
import com.bysj.service.CategoryService;
import com.bysj.service.CustomizeService;
import com.bysj.service.ProductService;
import com.bysj.utils.MessageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/admin/product")
public class SysProductController extends BaseController {

    @Autowired
    ProductService productService;
    @Autowired
    CustomizeService<Product> customizeService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public MessageHelper getData(HttpServletRequest request,
                                 @RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "intro", required = false) String intro) {
        LambdaQueryWrapper<Product> lambda = new QueryWrapper<Product>().lambda();
        if (StringUtils.isNotBlank(title)) {
            lambda.like(Product::getTitle, title);
        }
        if (StringUtils.isNotBlank(intro)) {
            lambda.like(Product::getIntro, intro);
        }
        IPage<Product> page = productService.page(getPage(request), lambda);
        return MessageHelper.ok(page);
    }

    @GetMapping("/detail/{id}")
    public MessageHelper detail(@PathVariable(value = "id") Integer id) {
        String sql = "SELECT * FROM t_product WHERE id = " + id;
        Map<String, Object> one = customizeService.getOne(sql);
        Integer categoryId = (one.get("categoryId") == null ? null : Integer.valueOf(one.get("categoryId").toString()));
        Category category = categoryService.getById(categoryId);
        if(category != null){
            if(category.getParentId()==0){
                one.put("categoryName",category.getCategoryName());
            }else{
                one.put("categoryName2",category.getCategoryName());
                Category category2 = categoryService.getById(category.getParentId());
                one.put("categoryName",category2.getCategoryName());
            }
        }
        return MessageHelper.ok(one);
    }
}
