package com.bysj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bysj.beans.Product;
import com.bysj.mapper.ProductMapper;
import com.bysj.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
}
