package by.academy.it.rest.dao;

import by.academy.it.rest.entity.Employee;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {
    List<Employee> getEmployee();
}
