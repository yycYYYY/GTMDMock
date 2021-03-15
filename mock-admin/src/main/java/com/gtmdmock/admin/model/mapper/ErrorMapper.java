package com.gtmdmock.admin.model.mapper;

import com.gtmdmock.admin.model.entity.Error;
import com.gtmdmock.admin.model.entity.ErrorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErrorMapper {
    long countByExample(ErrorExample example);

    int deleteByExample(ErrorExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Error record);

    int insertSelective(Error record);

    List<Error> selectByExample(ErrorExample example);

    Error selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Error record, @Param("example") ErrorExample example);

    int updateByExample(@Param("record") Error record, @Param("example") ErrorExample example);

    int updateByPrimaryKeySelective(Error record);

    int updateByPrimaryKey(Error record);
}