package com.bysj.controller.admin;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bysj.beans.Product;
import com.bysj.controller.BaseController;
import com.bysj.service.ProductService;
import com.bysj.utils.MessageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/product")
public class SysProductController extends BaseController {

    @Autowired
    ProductService productService;

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
}
