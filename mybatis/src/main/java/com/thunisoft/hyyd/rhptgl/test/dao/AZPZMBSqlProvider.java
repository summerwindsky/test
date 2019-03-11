package com.thunisoft.hyyd.rhptgl.test.dao;

import com.thunisoft.hyyd.rhptgl.test.model.AZPZMB;
import org.apache.ibatis.jdbc.SQL;

public class AZPZMBSqlProvider {

    public String insertSelective(AZPZMB record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_yw_azpzmb");
        
        if (record.getcId() != null) {
            sql.VALUES("c_id", "#{cId,jdbcType=INTEGER}");
        }
        
        if (record.getcPzxxm() != null) {
            sql.VALUES("c_pzxxm", "#{cPzxxm,jdbcType=VARCHAR}");
        }
        
        if (record.getcMrz() != null) {
            sql.VALUES("c_mrz", "#{cMrz,jdbcType=VARCHAR}");
        }
        
        if (record.getcAzzjxxId() != null) {
            sql.VALUES("c_azzjxx_id", "#{cAzzjxxId,jdbcType=NUMERIC}");
        }
        
        if (record.getcBb() != null) {
            sql.VALUES("c_bb", "#{cBb,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AZPZMB record) {
        SQL sql = new SQL();
        sql.UPDATE("t_yw_azpzmb");
        
        if (record.getcPzxxm() != null) {
            sql.SET("c_pzxxm = #{cPzxxm,jdbcType=VARCHAR}");
        }
        
        if (record.getcMrz() != null) {
            sql.SET("c_mrz = #{cMrz,jdbcType=VARCHAR}");
        }
        
        if (record.getcAzzjxxId() != null) {
            sql.SET("c_azzjxx_id = #{cAzzjxxId,jdbcType=NUMERIC}");
        }
        
        if (record.getcBb() != null) {
            sql.SET("c_bb = #{cBb,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("c_id = #{cId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}