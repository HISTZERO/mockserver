package com.example.mockserver.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity(name = "api")
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private Long statusCodeId;
    

    private Long projectId;

    private String path;

    private String name;

    private String content;

    private int deleteFlag;

    private Timestamp createdDatetime;

    private Timestamp updatedDatetime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_code_id")
    @Fetch(FetchMode.JOIN)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Api() {
    }

    public Api(Long id, Long projectId, String path, String name, String content, int deleteFlag, Timestamp createdDatetime, Timestamp updatedDatetime, Status status) {
        this.id = id;
        this.projectId = projectId;
        this.path = path;
        this.name = name;
        this.content = content;
        this.deleteFlag = deleteFlag;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}
