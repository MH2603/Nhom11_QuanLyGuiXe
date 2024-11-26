package com.example.quanlyguixe.screen.employee;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quanlyguixe.data.model.Employees;
import com.example.quanlyguixe.data.repo.EmployeeRepository;
import com.example.quanlyguixe.util.base.BaseViewModel;
import com.example.quanlyguixe.util.interfaces.IResultListener;

import java.util.List;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmployeeViewModel extends BaseViewModel {
    private final EmployeeRepository employeeRepository;

    private final MutableLiveData<List<Employees>> _employees = new MutableLiveData<>();

    public LiveData<List<Employees>> getEmployees() {
        return _employees;
    }

    @Inject
    public EmployeeViewModel(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

        getAllEmployees();
    }

    public void getAllEmployees() {
        registerDisposable(
                executeTaskWithLoading(
                        employeeRepository.getAllEmployees(),
                        new IResultListener<List<Employees>>() {
                            @Override
                            public void onSuccess(List<Employees> data) {
                                _employees.setValue(data);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.postValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void insertEmployee(Employees employees) {
        registerDisposable(
                executeTaskWithLoading(
                        employeeRepository.insertEmployee(employees),
                        new IResultListener<Long>() {
                            @Override
                            public void onSuccess(Long data) {
                                _backToPreviousScreen.setValue(true);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.postValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void updateEmployee(Employees employees) {
        registerDisposable(
                executeTaskWithLoading(
                        employeeRepository.updateEmployee(employees),
                        new IResultListener<Integer>() {
                            @Override
                            public void onSuccess(Integer data) {
                                _backToPreviousScreen.setValue(true);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.postValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void deleteEmployee(Employees employees) {
        registerDisposable(
                executeTaskWithLoading(
                        employeeRepository.deleteEmployee(employees),
                        new IResultListener<Integer>() {
                            @Override
                            public void onSuccess(Integer data) {

                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.postValue(throwable.getMessage());
                            }
                        }
                )
        );
    }
}
