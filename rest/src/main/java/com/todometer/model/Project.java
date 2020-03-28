package com.todometer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Miguel Tortosa Calabuig
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taskProject")
    @JsonIgnoreProperties("taskProject")
    private List<Task> tasks;
}
