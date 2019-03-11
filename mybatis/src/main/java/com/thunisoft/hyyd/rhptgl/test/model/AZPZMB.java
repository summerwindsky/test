package com.thunisoft.hyyd.rhptgl.test.model;

import java.math.BigDecimal;

public class AZPZMB {
    private Integer cId;

    private String cPzxxm;

    private String cMrz;

    private BigDecimal cAzzjxxId;

    private String cBb;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getcPzxxm() {
        return cPzxxm;
    }

    public void setcPzxxm(String cPzxxm) {
        this.cPzxxm = cPzxxm == null ? null : cPzxxm.trim();
    }

    public String getcMrz() {
        return cMrz;
    }

    public void setcMrz(String cMrz) {
        this.cMrz = cMrz == null ? null : cMrz.trim();
    }

    public BigDecimal getcAzzjxxId() {
        return cAzzjxxId;
    }

    public void setcAzzjxxId(BigDecimal cAzzjxxId) {
        this.cAzzjxxId = cAzzjxxId;
    }

    public String getcBb() {
        return cBb;
    }

    public void setcBb(String cBb) {
        this.cBb = cBb == null ? null : cBb.trim();
    }
}