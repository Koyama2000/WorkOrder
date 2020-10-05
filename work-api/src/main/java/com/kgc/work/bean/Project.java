package com.kgc.work.bean;

import java.io.Serializable;

public class Project implements Serializable {
    private Integer id;

    private String projectname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname == null ? null : projectname.trim();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectname='" + projectname + '\'' +
                '}';
    }
}