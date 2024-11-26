package com.example.quanlyguixe.data.repo.local.employee;

import com.example.quanlyguixe.data.model.Employees;
import com.example.quanlyguixe.data.repo.IEmployeeDataSource;

import java.util.List;

import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class EmployeeLocalDataSource implements IEmployeeDataSource.Local {

    private final EmployeeDao employeesDao;

    @Inject
    public EmployeeLocalDataSource(EmployeeDao employeeDao) {
        this.employeesDao = employeeDao;
    }

    @Override
    public Single<List<Employees>> getAllEmployees() {
        return employeesDao.getAllEmployees();
    }

    @Override
    public Single<Employees> getEmployeeByID(int employeeID) {
        return employeesDao.getEmployeeByID(employeeID);
    }

    @Override
    public Single<Long> insertEmployee(Employees employees) {
        return employeesDao.insertEmployee(employees);
    }

    @Override
    public Single<Integer> updateEmployee(Employees employees) {
        return employeesDao.updateEmployee(employees);
    }

    @Override
    public Single<Integer> deleteEmployee(Employees employees) {
        return employeesDao.deleteEmployee(employees);
    }
}
