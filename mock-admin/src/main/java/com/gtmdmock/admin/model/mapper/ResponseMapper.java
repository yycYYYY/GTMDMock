package com.gtmdmock.admin.model.mapper;

import com.gtmdmock.admin.model.entity.Response;
import com.gtmdmock.admin.model.entity.ResponseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResponseMapper {
    long countByExample(ResponseExample example);

    int deleteByExample(ResponseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Response record);

    int insertSelective(Response record);

    List<Response> selectByExample(ResponseExample example);

    Response selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Response record, @Param("example") ResponseExample example);

    int updateByExample(@Param("record") Response record, @Param("example") ResponseExample example);

    int updateByPrimaryKeySelective(Response record);

    int updateByPrimaryKey(Response record);
}