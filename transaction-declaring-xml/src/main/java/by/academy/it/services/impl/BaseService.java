package by.academy.it.services.impl;

import by.academy.it.rest.dao.Dao;
import by.academy.it.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class BaseService<T> implements IService<T> {
    @Autowired
    private Dao<T> baseDao;
    @Override
    public T add(T t) {
        return baseDao.add(t);
    }
    @Override
    public T update(T t) {
        return null;
    }
    @Override
    public T get(Serializable id) {
        return baseDao.get(id);
    }
    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }
}
