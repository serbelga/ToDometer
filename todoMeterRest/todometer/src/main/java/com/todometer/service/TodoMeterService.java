/**
 * ***************************************************************** Copyright (c) 2019 FLIT2go to
 * Present. All rights reserved. *****************************************************************
 */
package com.todometer.service;


import com.todometer.model.Project;
import com.todometer.model.Task;

import java.util.List;

/** @author miguelet */
public interface TodoMeterService {
   Task getTaskById(Integer id);
   Task addTask(Task task);
   List<Task> getAllTasks();
   Project getProjectById(Integer id);
   List<Project> getAllProjects();
   Project addProject(Project project);
}
