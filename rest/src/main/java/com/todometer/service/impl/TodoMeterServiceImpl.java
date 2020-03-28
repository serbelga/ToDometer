package com.todometer.service.impl;

import com.todometer.model.Project;
import com.todometer.model.Task;
import com.todometer.repository.ProjectRepository;
import com.todometer.repository.TaskRepository;
import com.todometer.service.TodoMeterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miguel Tortosa Calabuig
 */
@Service("RentalService")
@Slf4j
public class TodoMeterServiceImpl extends GenericServiceImpl<Task,Integer> implements TodoMeterService {
   @Autowired
    TaskRepository taskRepository;
   @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Task> getAllTasks() {
      return  taskRepository.findAll();
    }


    @Override
    public Task getTaskById(Integer id) {
        return  taskRepository.findById(id).orElse(null);
    }

    @Override
    public Task addTask(Task task) {
        taskRepository.saveAndFlush(task);
        return  task;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project addProject(Project project) {
       projectRepository.saveAndFlush(project);
       return project;
    }

    @Override
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElse(null);
    }

}
