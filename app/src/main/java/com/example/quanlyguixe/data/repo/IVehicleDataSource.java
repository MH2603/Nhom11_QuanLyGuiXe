package com.example.quanlyguixe.data.repo;


import com.example.quanlyguixe.data.model.Vehicle;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IVehicleDataSource {
    interface Local {
        Single<List<Vehicle>> getAllVehicles();

        Single<Vehicle> getVehicleByID(int vehicleID);

        Single<Long> insertVehicle(Vehicle vehicle);

        Single<Integer> updateVehicle(Vehicle vehicle);

        Single<Integer> deleteVehicle(List<Vehicle> vehicles);
    }
}
