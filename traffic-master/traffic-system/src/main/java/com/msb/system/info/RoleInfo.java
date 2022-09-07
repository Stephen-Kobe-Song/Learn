package com.msb.system.info;

import javax.persistence.*;
import java.util.List;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.info
 * @Author: song
 * @CreateTime: 2022-06-03  23:55
 * @Description: TODO
 * @Version: 1.0
 */
@Entity
@Table(name ="t_role")
public class RoleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;
    @Column
    private String rname;
    @Column
    private int rtype;
    @Column
    private String rdesc;

    @ManyToMany(mappedBy = "roles")
    private List<UserInfo> users;

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

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "rid=" + rid +
                ", rname='" + rname + '\'' +
                ", rtype='" + rtype + '\'' +
                ", rdesc='" + rdesc + '\'' +
                '}';
    }
}
