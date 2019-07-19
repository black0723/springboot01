package com.bysj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.beans.Category;
import com.bysj.mapper.CategoryMapper;
import com.bysj.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
