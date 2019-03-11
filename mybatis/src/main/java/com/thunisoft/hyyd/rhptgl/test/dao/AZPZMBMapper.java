package com.thunisoft.hyyd.rhptgl.test.dao;

import com.thunisoft.hyyd.rhptgl.test.model.AZPZMB;

public interface AZPZMBMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(AZPZMB record);

    int insertSelective(AZPZMB record);

    AZPZMB selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(AZPZMB record);

    int updateByPrimaryKey(AZPZMB record);
}