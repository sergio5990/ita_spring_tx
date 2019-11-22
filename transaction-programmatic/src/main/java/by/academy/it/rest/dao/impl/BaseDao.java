package by.academy.it.rest.dao.impl;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.academy.it.rest.dao.Dao;

import org.springframework.stereotype.Repository;

import lombok.Getter;

@Repository
public class BaseDao<T> implements Dao<T> {
    Class<T> clazz;
    @PersistenceContext
    @Getter
    private EntityManager em;

    @Override
    public T add(T t) {
        em.persist(t);
        return t;
    }
    @Override
    public T get(Serializable id) {
        return em.find(clazz, id);
    }
    @Override
    public T update(T t) {
        em.merge(t);
        return t;
    }
    @Override
    public void delete(Serializable id) {
        T t = em.find(clazz, id);
        em.remove(t);
    }
}