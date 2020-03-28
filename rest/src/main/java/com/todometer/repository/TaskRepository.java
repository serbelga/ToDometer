package com.todometer.repository;

import com.todometer.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Miguel Tortosa Calabuig
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
