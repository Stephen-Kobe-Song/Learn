package com.msb.system.entity;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.entity
 * @Author: song
 * @CreateTime: 2022-05-27  23:34
 * @Description: 这里是前端出过来的实体类！
 * @Version: 1.0
 */

public class UserEntity {

    private long uid;
    private String uname;
    private String uaccount;
    private String upass;
    private String umail;
    private String uphone;
    private String desc;
    private String t1;
    private Date utime;

    private String startTime;
    private String endTime;

    private int currentPage = 0;
    private int pageSize = 3;

    private String sort;
    private String sortType = "ASC";
    // 角色编号；添加或者修改的时候，需要去给用户指定角色信息。
    private String rolesStr;

    public String getRolesStr() {
        return rolesStr;
    }

    public void setRolesStr(String rolesStr) {
        this.rolesStr = rolesStr;
    }

    private List<RoleEntity> roles;

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }


    public String getUaccount() {
        return uaccount;
    }

    public void setUaccount(String uaccount) {
        this.uaccount = uaccount;
    }


    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }


    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }


    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", uaccount='" + uaccount + '\'' +
                ", upass='" + upass + '\'' +
                ", umail='" + umail + '\'' +
                ", uphone='" + uphone + '\'' +
                ", desc='" + desc + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}

