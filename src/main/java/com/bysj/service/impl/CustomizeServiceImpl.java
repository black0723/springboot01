package com.bysj.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bysj.mapper.CustomizeMapper;
import com.bysj.service.CustomizeService;
import com.bysj.utils.Map2ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomizeServiceImpl<T> implements CustomizeService<T> {

    @Autowired
    CustomizeMapper mapper;

    @Override
    public List<T> getBySql(Class<?> classzz, String sql) {
        List<Map<String, Object>> list = mapper.getBySql(sql);
        List<T> resultList = new Map2ModelUtils<T>().toList(classzz, list);
        return resultList == null ? new ArrayList<>() : resultList;
    }

    @Override
    public IPage<T> getBySql(Class<?> classzz, String sql, int pageIndex, int pageSize) {
        IPage page = new Page(pageIndex, pageSize);
        List<Map<String, Object>> list = mapper.getBySql(sql);
        List<T> resultList = new Map2ModelUtils<T>().toList(classzz, list);
        if (resultList == null) {
            return page;
        }
        page.setRecords(resultList);
        return page;
    }

    @Override
    public T getOne(Class<?> classzz, String sql) {
        Map<String, Object> map = mapper.getOne(sql);
        return new Map2ModelUtils<T>().toModel(classzz, map);
    }

    @Override
    public List<Map<String, Object>> getBySql(String sql) {
        return mapper.getBySql(sql);
    }

    @Override
    public Map<String, Object> getOne(String sql) {
        return mapper.getOne(sql);
    }

    @Override
    public void execute(String sql) {
        mapper.execute(sql);
    }
}
