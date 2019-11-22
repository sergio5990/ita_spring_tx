package by.academy.it.rest.dao.impl;

import java.util.List;

import by.academy.it.rest.dao.EmployeeDao;
import org.springframework.stereotype.Repository;

import by.academy.it.rest.entity.Employee;

@Repository
public class EmployeeDaoImpl extends BaseDao<Employee> implements EmployeeDao {
    public EmployeeDaoImpl() {
        super();
        clazz = Employee.class;
    }

    @Override
    public List<Employee> getEmployee() {
        return getEm().createQuery("from Employee").getResultList();
    }
}
