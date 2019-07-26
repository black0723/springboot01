package com.bysj.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    /**
     * 获取列表
     *
     * @param request
     * @param title
     * @param intro
     * @return
     */
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

    /**
     * 获取详情
     *
     * SELECT * ,
     * IF(
     * 	a.parentCategoryId=0,
     * 	(SELECT categoryName FROM t_category AS t WHERE t.id = a.categoryId) ,
     * 	(SELECT categoryName FROM t_category AS t WHERE t.id = a.parentCategoryId)
     * ) AS categoryName1,
     * IF(
     * 	a.parentCategoryId>0,
     * 	(SELECT categoryName FROM t_category AS t WHERE t.id = a.categoryId) ,
     * 	NULL
     * ) AS categoryName2
     * FROM t_product AS a
     *
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public MessageHelper detail(@PathVariable(value = "id") Integer id) {
        String sql = "SELECT * , \n" +
                "IF(\n" +
                "\ta.parentCategoryId=0,\n" +
                "\t(SELECT categoryName FROM t_category AS t WHERE t.id = a.categoryId) ,\n" +
                "\t(SELECT categoryName FROM t_category AS t WHERE t.id = a.parentCategoryId)\n" +
                ") AS categoryName1,\n" +
                "IF(\n" +
                "\ta.parentCategoryId>0,\n" +
                "\t(SELECT categoryName FROM t_category AS t WHERE t.id = a.categoryId) ,\n" +
                "\tNULL\n" +
                ") AS categoryName2\n" +
                "FROM t_product AS a WHERE a.id = " + id;
        Map<String, Object> one = customizeService.getOne(sql);
        return MessageHelper.ok(one);
    }

    /**
     * 上架/下架
     *
     * @param id
     * @param status
     * @return
     */
    @GetMapping("/updateStatus")
    public MessageHelper updateStatus(@RequestParam(value = "id") Integer id,
                                      @RequestParam(value = "status") String status) {
        productService.updateById(new Product(id, status));
        return MessageHelper.ok();
    }
}
