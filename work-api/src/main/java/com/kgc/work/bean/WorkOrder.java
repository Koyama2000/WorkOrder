package com.kgc.work.bean;

import java.io.Serializable;
import java.util.Date;

public class WorkOrder implements Serializable {
    private Integer id;

    private Integer projectid;

    private String executor;

    private String description;

    private Integer orderlevel;

    private Date createdate;

    private String projectname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor == null ? null : executor.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getOrderlevel() {
        return orderlevel;
    }

    public void setOrderlevel(Integer orderlevel) {
        this.orderlevel = orderlevel;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    @Override
    public String toString() {
        return "WorkOrder{" +
                "id=" + id +
                ", projectid=" + projectid +
                ", executor='" + executor + '\'' +
                ", description='" + description + '\'' +
                ", orderlevel=" + orderlevel +
                ", createdate=" + createdate +
                ", projectname='" + projectname + '\'' +
                '}';
    }
}