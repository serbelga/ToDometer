package com.todometer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

/**
 * @author Miguel Tortosa Calabuig
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
    private Integer taskState;
    @Column
    private Integer tag;
    @JoinColumn
    @ManyToOne()
    @JsonIgnoreProperties("tasks")
    Project taskProject;
}
