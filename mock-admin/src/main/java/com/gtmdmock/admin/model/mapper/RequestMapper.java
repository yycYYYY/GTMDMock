package com.gtmdmock.admin.model.mapper;

import com.gtmdmock.admin.model.entity.Request;
import com.gtmdmock.admin.model.entity.RequestExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RequestMapper {
    long countByExample(RequestExample example);

    int deleteByExample(RequestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Request record);

    int insertSelective(Request record);

    List<Request> selectByExample(RequestExample example);

    Request selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Request record, @Param("example") RequestExample example);

    int updateByExample(@Param("record") Request record, @Param("example") RequestExample example);

    int updateByPrimaryKeySelective(Request record);

    int updateByPrimaryKey(Request record);
}