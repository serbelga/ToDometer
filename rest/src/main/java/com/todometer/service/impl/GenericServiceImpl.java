package com.todometer.service.impl;

import com.todometer.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Miguel Tortosa Calabuig
 */
public abstract class GenericServiceImpl<T, K extends Serializable> implements GenericService<T, K> {

    @Autowired
    private JpaRepository<T, K> jpaRepository;

    /**
     * Checks if the object passed as a parameter is null or empty
     *
     * @param obj
     * @return true, if the object isn't null or empty false in other case
     */
    protected boolean check(final Object obj) {
        return obj != null && !obj.toString().isEmpty();
    }

    @Override
    public List<T> getAll() {
        return jpaRepository.findAll();
    }

    @Override
    public T getById(final K code) {
        final boolean check = check(code);
        return check ? jpaRepository.getOne(code) : null;
    }

    @Override
    public void add(final T object) {
        jpaRepository.save(object);
    }

    @Override
    public void delete(final T object) throws Exception {
        jpaRepository.delete(object);
    }

    @Override
    public void update(final T object) {
        jpaRepository.save(object);
    }

}
