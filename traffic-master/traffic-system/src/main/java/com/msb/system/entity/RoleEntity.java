package com.msb.system.entity;

import java.util.List;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.entity
 * @Author: song
 * @CreateTime: 2022-06-03  23:42
 * @Description: TODO
 * @Version: 1.0
 */

public class RoleEntity {

    private long rid;
    private String rname;
    private int  rtype;
    private String rdesc;

    private List<UserEntity> users;

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public int  getRtype() {
        return rtype;
    }

    public void setRtype(int rtype) {
        this.rtype = rtype;
    }

    public String getRdesc() {
        return rdesc;
    }

    public void setRdesc(String rdesc) {
        this.rdesc = rdesc;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", rtype='" + rtype + '\'' +
                ", rdesc='" + rdesc + '\'' +
                '}';
    }
}
