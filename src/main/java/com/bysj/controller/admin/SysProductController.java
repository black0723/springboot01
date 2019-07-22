package com.bysj.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bysj.beans.Product;
import com.bysj.controller.BaseController;
import com.bysj.service.ProductService;
import com.bysj.utils.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/product")
public class SysProductController extends BaseController {

    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public MessageHelper getData(HttpServletRequest request) {
        IPage page = productService.page(getPage(request));
        return MessageHelper.ok(page);
    }
}
