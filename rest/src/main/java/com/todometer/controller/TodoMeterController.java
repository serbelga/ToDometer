package com.todometer.controller;

import com.todometer.model.Project;
import com.todometer.model.Task;
import com.todometer.service.TodoMeterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Miguel Tortosa Calabuig
 */
@RestController
@Slf4j
public class TodoMeterController {
    @Autowired
    TodoMeterService srvTodoMeter;

    @GetMapping(path = "/v1/tasks")
    //@PreAuthorize("hasAuthority('USER') ")
    public Object getAllTasks() {
       return new ResponseEntity<>(srvTodoMeter.getAllTasks(), HttpStatus.OK);
    }


    @PostMapping(path = "/v1/task")
    //@PreAuthorize("hasAuthority('USER') ")
    public Object addTask(@RequestBody Task task) {
        return new ResponseEntity<>(srvTodoMeter.addTask(task), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/v1/task/{id}")
    //@PreAuthorize("hasAuthority('USER') ")
    public Object findTaskById(@PathVariable Integer id) {
        return new ResponseEntity<>(srvTodoMeter.getTaskById(id), HttpStatus.OK);
    }

    //project
    @GetMapping(path = "/v1/projects")
    //@PreAuthorize("hasAuthority('USER') ")
    public Object getAllProjects() {
        return new ResponseEntity<>(srvTodoMeter.getAllProjects(), HttpStatus.OK);
    }

    @PostMapping(path = "/v1/project")
    //@PreAuthorize("hasAuthority('USER') ")
    public Object addProject(@RequestBody Project project) {
        return new ResponseEntity<>(srvTodoMeter.addProject(project), HttpStatus.OK);
    }

    @GetMapping(path = "/v1/project/{id}")
    //@PreAuthorize("hasAuthority('USER') ")
    public Object findProjectId(@PathVariable Integer id) {
        return new ResponseEntity<>(srvTodoMeter.getProjectById(id), HttpStatus.OK);
    }

}
