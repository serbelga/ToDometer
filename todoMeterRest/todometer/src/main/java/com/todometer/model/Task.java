package com.todometer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * @Author Miguelet
 */
@Entity
@Table
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    @Column
    private String taskName;
    @Column
    private String taskDescription;
    @Column
    private String taskState;
    @Column
    private String tag;
    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tasks")
    Project taskProjectId;
}
