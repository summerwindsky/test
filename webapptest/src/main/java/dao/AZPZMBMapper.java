package dao;


import model.AZPZMB;

public interface AZPZMBMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(AZPZMB record);

    int insertSelective(AZPZMB record);

    AZPZMB selectByPrimaryKey(Integer cId);

    int updateByPrimaryKeySelective(AZPZMB record);

    int updateByPrimaryKey(AZPZMB record);
}