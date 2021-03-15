package com.gtmdmock.admin.model.mapper;

import com.gtmdmock.admin.model.entity.Expectations;
import com.gtmdmock.admin.model.entity.ExpectationsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpectationsMapper {
    long countByExample(ExpectationsExample example);

    int deleteByExample(ExpectationsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Expectations record);

    int insertSelective(Expectations record);

    List<Expectations> selectByExample(ExpectationsExample example);

    Expectations selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Expectations record, @Param("example") ExpectationsExample example);

    int updateByExample(@Param("record") Expectations record, @Param("example") ExpectationsExample example);

    int updateByPrimaryKeySelective(Expectations record);

    int updateByPrimaryKey(Expectations record);
}