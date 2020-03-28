package com.todometer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Miguelet
 */
@Entity
@Table
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    @Column
    private String projectName;
    @Column
    private String projectDescription;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskProjectId")
    @JsonIgnoreProperties("taskProjectId")
    private List<Task> tasks;
}
