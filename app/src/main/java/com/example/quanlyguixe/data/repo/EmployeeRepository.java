package com.example.quanlyguixe.data.repo;

import com.example.quanlyguixe.data.model.Employees;

import java.util.List;

import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class EmployeeRepository implements IEmployeeDataSource.Local {

    private final IEmployeeDataSource.Local local;

    @Inject
    public EmployeeRepository(IEmployeeDataSource.Local local) {
        this.local = local;
    }
    @Override
    public Single<List<Employees>> getAllEmployees() {
        return local.getAllEmployees();
    }

    @Override
    public Single<Employees> getEmployeeByID(int employeeID) {
        return local.getEmployeeByID(employeeID);
    }

    @Override
    public Single<Long> insertEmployee(Employees employees) {
        return local.insertEmployee(employees);
    }

    @Override
    public Single<Integer> updateEmployee(Employees employees) {
        return local.updateEmployee(employees);
    }

    @Override
    public Single<Integer> deleteEmployee(Employees employees) {
        return local.deleteEmployee(employees);
    }
}
