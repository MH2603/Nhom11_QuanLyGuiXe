package com.example.quanlyguixe.data.repo.local.vehicle;

import com.example.quanlyguixe.data.model.Vehicle;
import com.example.quanlyguixe.data.repo.IVehicleDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class VehicleLocalDataSource implements IVehicleDataSource.Local {

    private final VehicleDao vehicleDao;

    @Inject
    public VehicleLocalDataSource(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Single<List<Vehicle>> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }

    @Override
    public Single<Vehicle> getVehicleByID(int vehicleID) {
        return vehicleDao.getVehicleByID(vehicleID);
    }

    @Override
    public Single<Long> insertVehicle(Vehicle vehicle) {
        return vehicleDao.insertVehicle(vehicle);
    }

    @Override
    public Single<Integer> updateVehicle(Vehicle vehicle) {
        return vehicleDao.updateVehicle(vehicle);
    }

    @Override
    public Single<Integer> deleteVehicle(List<Vehicle> vehicles) {
        return vehicleDao.deleteVehicle(vehicles);
    }

}
