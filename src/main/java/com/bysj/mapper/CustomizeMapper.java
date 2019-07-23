package com.bysj.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomizeMapper {

    List<Map<String, Object>> getBySql(@Param(value = "sql") String sql);

    Map<String, Object> getOne(@Param(value = "sql") String sql);

    void execute(@Param(value = "sql") String sql);
}
