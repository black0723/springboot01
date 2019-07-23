package com.bysj.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface CustomizeService<T> {

    List<T> getBySql(Class<?> classzz, String sql);

    IPage<T> getBySql(Class<?> classzz, String sql, int pageIndex, int pageSize);

    List<Map<String, Object>> getBySql(String sql);

    T getOne(Class<?> classzz, String sql);

    Map<String, Object> getOne(String sql);

    void execute(String sql);

}
