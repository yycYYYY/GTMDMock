package com.gtmdmock.admin.model.mapper;

import com.gtmdmock.admin.model.entity.Forward;
import com.gtmdmock.admin.model.entity.ForwardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ForwardMapper {
    long countByExample(ForwardExample example);

    int deleteByExample(ForwardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Forward record);

    int insertSelective(Forward record);

    List<Forward> selectByExample(ForwardExample example);

    Forward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Forward record, @Param("example") ForwardExample example);

    int updateByExample(@Param("record") Forward record, @Param("example") ForwardExample example);

    int updateByPrimaryKeySelective(Forward record);

    int updateByPrimaryKey(Forward record);
}