package by.academy.it.services.impl;

import java.io.Serializable;

import by.academy.it.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import by.academy.it.rest.dao.Dao;

@Service
public class BaseServiceImpl<T> implements IService<T> {
    @Autowired
    private Dao<T> baseDao;
    @Autowired
    TransactionTemplate transactionTemplate;
    @Override
    public T add(T t) {
        return transactionTemplate.execute(transactionStatus -> {
            try {
                return baseDao.add(t);
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
            }
            return null;
        });
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
