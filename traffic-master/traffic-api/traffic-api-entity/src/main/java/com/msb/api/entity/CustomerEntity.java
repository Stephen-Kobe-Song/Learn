package com.msb.api.entity;


public class CustomerEntity {

  private long cid;
  private String cname;
  private String phone;
  private String email;
  private String idcard;
  private long ctype;
  private long csex;
  private String remark;


  public long getCid() {
    return cid;
  }

  public void setCid(long cid) {
    this.cid = cid;
  }


  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }


  public long getCtype() {
    return ctype;
  }

  public void setCtype(long ctype) {
    this.ctype = ctype;
  }


  public long getCsex() {
    return csex;
  }

  public void setCsex(long csex) {
    this.csex = csex;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
