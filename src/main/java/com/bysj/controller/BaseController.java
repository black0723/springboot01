package com.bysj.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    /**
     * 获取分页
     *
     * @param request
     * @return
     */
    protected IPage getPage(HttpServletRequest request) {
        int pageIndex = 1;
        int pageSize = 5;
        String index = request.getParameter("pageIndex");
        String size = request.getParameter("pageSize");
        if (StringUtils.isNotBlank(index) && StringUtils.isNumeric(index)) {
            pageIndex = Integer.valueOf(index);
        }
        if (StringUtils.isNotBlank(size) && StringUtils.isNumeric(size)) {
            pageSize = Integer.valueOf(size);
        }
        return new Page(pageIndex,pageSize);
    }
}
