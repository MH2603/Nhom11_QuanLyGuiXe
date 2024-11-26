package com.example.quanlyguixe.data.repo;


import com.example.quanlyguixe.data.model.Vehicle;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class VehicleRepository implements IVehicleDataSource.Local{

    private final IVehicleDataSource.Local local;

    @Inject
    public VehicleRepository(IVehicleDataSource.Local local) {
        this.local = local;
    }

    @Inject

    @Override
    public Single<List<Vehicle>> getAllVehicles() {
        return local.getAllVehicles();
    }

    @Override
    public Single<Vehicle> getVehicleByID(int vehicleID) {
        return local.getVehicleByID(vehicleID);
    }

    @Override
    public Single<Long> insertVehicle(Vehicle vehicle) {
        return local.insertVehicle(vehicle);
    }

    @Override
    public Single<Integer> updateVehicle(Vehicle vehicle) {
        return local.updateVehicle(vehicle);
    }

    @Override
    public Single<Integer> deleteVehicle(List<Vehicle> vehicles) {
        return local.deleteVehicle(vehicles);
    }
}
