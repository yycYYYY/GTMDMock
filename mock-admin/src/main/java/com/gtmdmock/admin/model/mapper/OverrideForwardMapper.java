package com.gtmdmock.admin.model.mapper;

import com.gtmdmock.admin.model.entity.OverrideForward;
import com.gtmdmock.admin.model.entity.OverrideForwardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OverrideForwardMapper {
    long countByExample(OverrideForwardExample example);

    int deleteByExample(OverrideForwardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OverrideForward record);

    int insertSelective(OverrideForward record);

    List<OverrideForward> selectByExample(OverrideForwardExample example);

    OverrideForward selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OverrideForward record, @Param("example") OverrideForwardExample example);

    int updateByExample(@Param("record") OverrideForward record, @Param("example") OverrideForwardExample example);

    int updateByPrimaryKeySelective(OverrideForward record);

    int updateByPrimaryKey(OverrideForward record);
}